/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>  {
    private Node first;
    private Node last;
    private int n;
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }
    private void nullCheck(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
    }

    public boolean isEmpty() { return n == 0; }
    public int size() {
        return n;
    }
    public void addFirst(Item item) {
        nullCheck(item);
        if (n == 0) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else {
            Node oldFirst = this.first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        n++;
    }
    public void addLast(Item item) {
        nullCheck(item);
        if (n == 0) {
            last = new Node();
            last.item = item;
            first = last;
        }
        else {
            Node oldLast = this.last;
            last = new Node();
            last.item = item;
            last.prev = oldLast;
            oldLast.next = last;
        }
        n++;
    }
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        if (n > 1) {
            first = first.next;
            first.prev = null;
        }
        else {
            first = null;
            last = null;
        }
        n--;
        if (isEmpty()) last = null;
        return item;
    }
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        if (n > 1) {
            last = last.prev;
            last.next = null;
        }
        else {
            first = null;
            last = null;
        }
        n--;
        if (isEmpty()) first = null;
        return item;

    }
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }
    private class LinkedIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(2);
        deque.addFirst(4);
        deque.addFirst(6);
        System.out.println(deque.size());
        for (int a: deque)
            System.out.println(a);
        deque.addLast(2);
        deque.addLast(4);
        deque.addLast(6);
        if (!deque.isEmpty())
            System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        Iterator<Integer> iter = deque.iterator();

    }
}
