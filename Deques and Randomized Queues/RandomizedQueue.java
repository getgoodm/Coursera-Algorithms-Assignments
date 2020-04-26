import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] a;
    public RandomizedQueue() {
        a = (Item[]) new Object[1];
        n = 0;
    }
    private void nullCheck(Item item) { if (item == null) throw new IllegalArgumentException(); }
    private void resize(int newLength) {
        Item[] copy = (Item[]) new Object[newLength];
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }
    public boolean isEmpty() { return n == 0; }
    public int size() { return n; }
    public void enqueue(Item item) {
        nullCheck(item);
        if (n == a.length) resize(2*a.length);
        a[n++] = item;
    }
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int randPos = StdRandom.uniform(n);
        Item item = a[randPos];
        a[randPos] = a[n-1];
        a[n-1] = null;
        n--;
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return a[StdRandom.uniform(n)];
    }
    public Iterator<Item> iterator() { return new RandomIterator(n); }
    private class RandomIterator implements Iterator<Item> {
        private Item[] arr;
        private int j;
        public RandomIterator(int n) {
            j = 0;
            arr = (Item[]) new Object[n];
            for (int i = 0; i < n; i++)
                arr[i] = a[i];
            StdRandom.shuffle(arr);
        }
        public boolean hasNext() {
            return j < n;
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return arr[j++];
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
        for (int i = 0; i < n; i++)
            System.out.println(queue.dequeue());
        System.out.println(queue.isEmpty());
        queue.enqueue(5);
        queue.enqueue(6);
        System.out.println(queue.sample());
        System.out.println(queue.size());
        Iterator<Integer> iter = queue.iterator();
    }
}
