package d9DerectorPattern;

import org.junit.Test;

import java.util.*;
import java.util.function.UnaryOperator;


public class App1 {
    @Test
    public void test() throws Exception {
        ArrayList<String> targetList = new ArrayList<>();
        targetList.add("aaa");
        targetList.add("aaa1");
        targetList.add("aaa2");
        targetList.add("aaa3");
        targetList.add("aaa4");
        List<String>  list=new ReadOnlyList<String>(targetList);

        //测试1：在这里直接添加是不可以的 会抛出异常
        //list.add("just test");//it will rise Exception read only list

        //测试2：但是这里却可以remove
        //所以还需要重新实现一个iterator 再次尝试
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.remove();


        list.stream().forEach(System.out::println);
    }
}

class ReadOnlyListIterator<E> implements ListIterator<E> {

    private ListIterator<E> target;

    public ReadOnlyListIterator(ListIterator<E> target) {
        super();
        this.target = target;
    }

    @Override
    public boolean hasNext() {
        return target.hasNext();
    }

    @Override
    public E next() {
        return target.next();
    }

    @Override
    public boolean hasPrevious() {
        return target.hasPrevious();
    }

    @Override
    public E previous() {
        return target.previous();
    }

    @Override
    public int nextIndex() {
        return target.nextIndex();
    }

    @Override
    public int previousIndex() {
        return target.previousIndex();
    }

    @Override
    public void remove() {
        throw new RuntimeException("此集合是只读的");
    }

    @Override
    public void set(E e) {
        throw new RuntimeException("此集合是只读的");

    }

    @Override
    public void add(E e) {
        throw new RuntimeException("此集合是只读的");

    }

}

class ReadOnlyList<E> implements List<E> {

    private List<E> targetList;

    public ReadOnlyList(List<E> targetList) {
        this.targetList = targetList;
    }

    @Override
    public int size() {
        return targetList.size();
    }

    @Override
    public boolean isEmpty() {
        return targetList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return targetList.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new ReadOnlyListIterator<>(targetList.listIterator());
    }

    @Override
    public Object[] toArray() {
        return targetList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return targetList.toArray(a);
    }

    @Override
    public boolean add(E e) {
        throw new RuntimeException("read only list.");
    }

    @Override
    public boolean remove(Object o) {
        throw new RuntimeException("read only list.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return targetList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new RuntimeException("read only list.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new RuntimeException("read only list.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new RuntimeException("read only list.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return targetList.retainAll(c);
    }

    @Override
    public void clear() {
        targetList.clear();
    }

    @Override
    public E get(int index) {
        return targetList.get(index);
    }

    @Override
    public E set(int index, E element) {
        return targetList.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        throw new RuntimeException("read only list.");
    }

    @Override
    public E remove(int index) {
        throw new RuntimeException("read only list.");
    }

    @Override
    public int indexOf(Object o) {
        return targetList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return targetList.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ReadOnlyListIterator<>(targetList.listIterator());
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ReadOnlyListIterator<>(targetList.listIterator(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new RuntimeException("read only list.");
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new RuntimeException("read only list.");
    }

    @Override
    public void sort(Comparator<? super E> c) {
        targetList.sort(c);
    }

    @Override
    public Spliterator<E> spliterator() {
       return targetList.spliterator();
    }
}