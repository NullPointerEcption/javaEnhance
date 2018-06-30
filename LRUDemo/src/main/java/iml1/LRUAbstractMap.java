package iml1;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.AbstractMap;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 第一种自实现的LRUMap
 *
 * @param <K>
 * @param <V>
 */
public class LRUAbstractMap<K, V> extends AbstractMap {

    /**
     * 检查是否超出期望线程
     */
    private ExecutorService checkTimePool;

    /**
     * map的最大size
     */
    private final static int MAX_SIZE = 1024;
    /**
     * 默认大小
     */
    private final static int DEFAULT_SIZE = 1024;

    private final static ArrayBlockingQueue<Node> QUEUE = new ArrayBlockingQueue(MAX_SIZE);
    /**
     * 数组长度
     */
    private int arraySize;
    /**
     * 数组
     */
    private Object[] arrays;

    /**
     * 判断是否停止flag
     */
    private volatile boolean flag = true;

    /**
     * 超时时间 一小时
     */
    private final static long EXPIRE_TIME = 60 * 60 * 1000L;

    /**
     * 整个Map的大小
     */
    private volatile AtomicInteger size;

    public LRUAbstractMap() {

        arraySize = DEFAULT_SIZE;
        arrays = new Object[arraySize];

        //开启一个线程检查最先放入队列的值是否超期
        execCheckTime();

    }

    private void execCheckTime() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("check-thread-%d")
                .setDaemon(true)
                .build();
        ThreadPoolExecutor checkTimePool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        checkTimePool.execute(new CheckTimeThread());
    }


    public Set<Entry> entrySet() {
        return super.keySet();
    }

    @Override
    public Object put(Object key, Object value) {
        int hash = hash(key);
        int index = hash % arraySize;

        Node currentNode = (Node) arrays[index];
        if (currentNode == null) {
            arrays[index] = new Node(null, null, key, value);

            QUEUE.offer((Node) arrays[index]);
            sizeUp();
        } else {
            Node cNode = currentNode;
            Node nNode = cNode;

            if (nNode.key == key) {
                cNode.val = value;
            }

            while (nNode.next != null) {
                if (nNode.key == key) {
                    nNode.val = value;
                    break;
                } else {
                    sizeUp();
                    Node node = new Node(nNode, null, key, value);

                    QUEUE.offer(currentNode);
                    cNode.next = node;
                }
                nNode = nNode.next;
            }
        }

        return null;
    }

    public Object get(Object key) {

        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = (Node) arrays[index];

        if (currentNode == null) {
            return null;
        }
        if (currentNode.next == null) {

            //更新时间
            currentNode.setUpdateTime(System.currentTimeMillis());

            //没有冲突
            return currentNode;

        }

        Node nNode = currentNode;
        while (nNode.next != null) {

            if (nNode.key == key) {

                //更新时间
                currentNode.setUpdateTime(System.currentTimeMillis());

                return nNode;
            }

            nNode = nNode.next;
        }

        return super.get(key);
    }


    @Override
    public Object remove(Object key) {

        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = (Node) arrays[index];

        if (currentNode == null) {
            return null;
        }

        if (currentNode.key == key) {
            sizeDown();
            arrays[index] = null;

            //移除队列
            QUEUE.poll();
            return currentNode;
        }

        Node nNode = currentNode;
        while (nNode.next != null) {

            if (nNode.key == key) {
                sizeDown();
                //在链表中找到了 把上一个节点的 next 指向当前节点的下一个节点
                nNode.pre.next = nNode.next;
                nNode = null;

                //移除队列
                QUEUE.poll();

                return nNode;
            }

            nNode = nNode.next;
        }

        return super.remove(key);
    }

    /**
     * 增加size
     */
    private void  sizeUp() {

        //在put值时候认为里边已经有数据了
        flag = true;

        if (size == null) {
            size = new AtomicInteger();
        }
        int size = this.size.incrementAndGet();
        if (size >= MAX_SIZE) {
            //找到队列头的数据
            Node node = QUEUE.poll();
            if (node == null) {
                throw new RuntimeException("data error");
            }

            //移除该 key
            Object key = node.key;
            remove(key);
            lruCallback();
        }

    }

    /**
     * copy HashMap 的 hash 实现
     *
     * @param key
     * @return
     */
    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private void lruCallback() {
    }

    /**
     * 数量减小
     */
    private void sizeDown() {

        if (QUEUE.size() == 0) {
            flag = false;
        }

        this.size.decrementAndGet();
    }

    @Override
    public int size() {
        return size.get();
    }

    /**
     * 链表
     */
    private static class Node {
        private Node next;
        private Node pre;
        private Object key;
        private Object val;
        private Long updateTime;

        public Node(Node pre, Node next, Object key, Object val) {
            this.pre = pre;
            this.next = next;
            this.key = key;
            this.val = val;
            this.updateTime = System.currentTimeMillis();
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }

    private class CheckTimeThread implements Runnable {

        public void run() {
            while (flag) {
                try {
                    Node node = QUEUE.poll();
                    if (node == null) {
                        continue;
                    }
                    Long updateTime = node.getUpdateTime();

                    if ((updateTime - System.currentTimeMillis()) >= EXPIRE_TIME) {
                        remove(node.key);
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}
