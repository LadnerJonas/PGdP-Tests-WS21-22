package pgdp.pingulib.datastructures.trees.heaps.binomial;

//import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerformanceTest {
  // Please keep in mind, that this test is most likely not the public performance test, but will give you
  // the possibility to test the speed of your implementation locally.
  // see https://zulip.in.tum.de/#narrow/stream/879-PGdP-W10H02--.20A.20Heap.20of.20Nodes/topic/performance.20Test.20.3F/near/419065
  @Test
    //@Disabled
  void insertPerformance() {
    BinoHeap<Integer> heap = new BinoHeap<>();
    
    long start = System.nanoTime();
    int i = 0;
    for (; i < 1e8 && start - System.nanoTime() > -1e9; i++) {
      heap.insert(i);
    }
    long end = System.nanoTime();
    System.out.println(i / 1e6 + " Millionen Elemente");
    System.out.println(String.format("%.0f", (end - start) / 1e6) + "ms");
    assertTrue(i >= 1e6);
  }
}
