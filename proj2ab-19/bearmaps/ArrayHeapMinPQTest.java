package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void test1() {
        ExtrinsicMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("c", 3);
        pq.add("i", 9);
        pq.add("g", 7);
        pq.add("d", 4);
        pq.add("a", 1);
        pq.add("h", 8);
        pq.add("e", 5);
        pq.add("b", 2);
        pq.add("c", 3);
        pq.add("d", 4);
        assertTrue(pq.contains("d"));
        assertFalse(pq.contains("z"));

        int i = 0;
        String[] expected = {"a", "b", "c", "c", "d", "d", "e", "g", "h", "i"};

        while (pq.size() > 1) {
            System.out.println(pq);
            assertEquals(expected[i], pq.removeSmallest());
            i += 1;
        }
        assertFalse(pq.contains("d"));
    }

    @Test
    public void test2(){
        int sum = 0;
        ExtrinsicMinPQ<String> pq1 = new ArrayHeapMinPQ<>();
        ExtrinsicMinPQ<String> pq2 = new NaiveMinPQ<>();
        Random r = new Random();

        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 100000; i += 1) {
            pq1.add("hi" + i, r.nextInt(100000));
        }
        for (int i = 0; i < 100000; i += 1) {
            pq1.contains("hi" + i);
        }
        System.out.println("Total time elapsed with ArrayHeapMinPQ: " + sw.elapsedTime() +  " seconds.");
        Stopwatch sw2 = new Stopwatch();
        for (int i = 0; i < 100000; i += 1) {
            pq2.add("hi" + i, r.nextInt(100000));
        }
        for (int i = 0; i < 100000; i += 1) {
            pq2.contains("hi" + i);
        }
        System.out.println("Total time elapsed with NaiveMinPQ: " + sw2.elapsedTime() +  " seconds.");
    }
}
