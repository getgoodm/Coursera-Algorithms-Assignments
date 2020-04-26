import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (k == 0) return;
        Deque<String> deque = new Deque<>();
        while (!StdIn.isEmpty()) {
            if (deque.size() < k) {
                int rand = StdRandom.uniform(2);
                if (rand == 0)
                    deque.addFirst(StdIn.readString());
                else deque.addLast(StdIn.readString());
            }
            else {
                int rand = StdRandom.uniform(2);
                if (rand == 0)
                    StdIn.readString();
                else {
                    int rand1 = StdRandom.uniform(2);
                    int rand2 = StdRandom.uniform(2);
                    if (rand1 == 0)
                        deque.removeFirst();
                    else deque.removeLast();
                    if (rand2 == 0)
                        deque.addFirst(StdIn.readString());
                    else deque.addLast(StdIn.readString());
                }
            }
        }
        for (String s: deque)
            System.out.println(s);
    }
}
