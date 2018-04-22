package d9DerectorPattern;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

public class App2 {

    @Test
    public void test() throws Exception {
        List<String> list = new SizeLimitList<>(5, new LinkedList<>());
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        //list.add("6");//??

        ListIterator<String> listIterator = list.listIterator();
        listIterator.add("6");

        list.stream().forEach(System.out::println);

    }
}

class SizeLimitIterator<E> implements ListIterator<E> {

    private ListIterator<E> listIterator;
    private int maxSize;
    private int currentSize;

    public SizeLimitIterator(ListIterator<E> listIterator, int maxSize) {
        this.listIterator = listIterator;
        this.maxSize = maxSize;
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasNext();
    }

    @Override
    public E next() {
        return listIterator.next();
    }

    @Override
    public boolean hasPrevious() {
        return listIterator.hasPrevious();
    }

    @Override
    public E previous() {
        return listIterator.previous();
    }

    @Override
    public int nextIndex() {
        return listIterator.nextIndex();
    }

    @Override
    public int previousIndex() {
        return listIterator.previousIndex();
    }

    @Override
    public void remove() {
        listIterator.remove();
    }

    @Override
    public void set(E e) {
        listIterator.set(e);
    }

    @Override
    public void add(E e) {
        //TODO 判断还能不能添加
        if (currentSize >= maxSize) {//fixme 获取当前list大小，判断是否小于最大值，我觉得根本就无法实现
            throw new RuntimeException("已超出最大容量，无法继续添加");
        }
        listIterator.add(e);
    }
}

class SizeLimitList<E> implements List<E> {

    private int maxSize;
    private List<E> target;

    public SizeLimitList(int maxSize, List<E> targetList) {
        this.maxSize = maxSize;
        this.target = targetList;
    }

    @Override
    public int size() {
        return target.size();
    }

    @Override
    public boolean isEmpty() {
        return target.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return target.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
//        return new SizeLimitIterator<>(target.listIterator(), maxSize);
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        return target.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return target.toArray(a);
    }

    @Override
    public boolean add(E e) {
        if (target.size() >= maxSize) {
            throw new RuntimeException("无法继续添加元素:"+e+"已经达到最大大小:"+maxSize);
        } else {
            return target.add(e);
        }
    }

    @Override
    public boolean remove(Object o) {
        return target.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return target.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (target.size() + c.size() > maxSize) {
            throw new RuntimeException("无法继续添加元素:"+c+"已经达到最大大小:"+maxSize);
        } else {
            return target.addAll(c);
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return target.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return target.retainAll(c);
    }

    @Override
    public void clear() {
        target.clear();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (target.size() + c.size() > maxSize) {
            throw new RuntimeException("无法继续添加元素:"+c+"已经达到最大大小:"+maxSize);
        } else {
            return target.addAll(index, c);
        }
    }

    @Override
    public E get(int index) {
        return target.get(index);
    }

    @Override
    public E set(int index, E element) {
        return target.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        if (target.size() >= maxSize) {
            throw new RuntimeException("无法继续添加元素:"+element+"已经达到最大大小:"+maxSize);
        } else {
            target.add(index, element);
        }
    }

    @Override
    public E remove(int index) {
        return target.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return target.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return target.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
//        return new SizeLimitIterator<>(target.listIterator());//todo：这里有漏洞
//        return new SizeLimitIterator<>(target.listIterator(), maxSize);
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
//        return target.listIterator(index);//todo：这里有漏洞
//        return new SizeLimitIterator<>(target.listIterator(index), maxSize);

        return new ListIterator<E>() {
            private final ListIterator<E> i
                    = target.listIterator();

            public boolean hasNext()     {return i.hasNext();}
            public E next()              {return i.next();}
            public boolean hasPrevious() {return i.hasPrevious();}
            public E previous()          {return i.previous();}
            public int nextIndex()       {return i.nextIndex();}
            public int previousIndex()   {return i.previousIndex();}

            public void remove() {
                i.remove();
            }
            public void set(E e) {
                i.remove();
            }
            public void add(E e) {
                if(size()<maxSize){
                    i.add(e);
                }else{
                    throw new RuntimeException("无法继续添加元素:"+e+"已经达到最大容量:"+maxSize);
                }
            }

            @Override
            public void forEachRemaining(Consumer<? super E> action) {
                i.forEachRemaining(action);
            }
        };

    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return target.subList(fromIndex, toIndex);
    }
}