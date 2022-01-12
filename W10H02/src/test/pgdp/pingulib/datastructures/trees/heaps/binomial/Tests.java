package pgdp.pingulib.datastructures.trees.heaps.binomial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Tests {

	private BinoHeap<Integer> heap1 = new BinoHeap<>();
	private BinoHeap<Integer> heap2 = new BinoHeap<>();
	private BinoHeap<TestObject> heap3 = new BinoHeap<>();
	private BinoHeap<TestObject> heap4 = new BinoHeap<>();

	// IMPORTANT: All tests are dependend on toString(), if your toString() in
	// BinoHeap does not work I am very sorry :/

	@Test
	void testInsert() {
		heap1.insert(null);
		assertEquals(0, heap1.getRoots().size());

		assertEquals(heap1.toString(), TestStrings.empty);
		// #######################################################################
		heap1.insert(0);
		assertEquals(heap1.toString(), TestStrings.heap1Element);
		// #######################################################################
		for (int i = 1; i < 15; i++) {
			heap1.insert(i);
		}
		assertEquals(heap1.toString(), TestStrings.heap15Elements);
		// #######################################################################
		heap1.insert(-1);
		assertEquals(heap1.toString(), TestStrings.heap15ElementsAndNegative1);

		// Not checked on edge cases: Just for an example!!
		heap3.insert(new TestObject("C"));
		heap3.insert(new TestObject("B"));
		heap3.insert(new TestObject("D"));
		assertEquals(heap3.getRoots().get(0).getValue().getValue(), "D");
		assertEquals(heap3.getRoots().get(1).getValue().getValue(), "B");
		assertEquals(heap3.getRoots().get(1).getChild(0).getValue().getValue(), "C");
	}

	@Test
	void testFindMin() {
		heap1 = new BinoHeap<Integer>();
		assertEquals(heap1.findMin(), null);
		heap1.insert(0);
		assertEquals(heap1.findMin(), 0);
		heap1.insert(1);
		assertEquals(heap1.findMin(), 0);
		heap1.insert(-1);
		assertEquals(heap1.findMin(), -1);

		heap3.insert(new TestObject("C"));
		heap3.insert(new TestObject("B"));
		assertEquals(heap3.findMin().getValue(), "B");
	}

	@Test
	void testDelMin() {
		heap1 = new BinoHeap<Integer>();
		heap1.delMin();
		assertEquals(heap1.toString(), TestStrings.empty);
		heap1.insert(0);
		heap1.delMin();
		assertEquals(heap1.toString(), TestStrings.empty);
		heap1.insert(0);
		heap1.insert(-1);
		heap1.delMin();
		assertEquals(heap1.toString(), TestStrings.heap1Element);
		heap1.delMin();
		assertEquals(heap1.toString(), TestStrings.empty);
		heap1.delMin();
		assertEquals(heap1.toString(), TestStrings.empty);
		for (int i = 0; i < 15; i++) {
			heap1.insert(i);
		}
		heap1.delMin();
		assertEquals(heap1.toString(), TestStrings.heap15ElementsWithout0);
		heap1.delMin();
		assertEquals(heap1.toString(), TestStrings.heap15ElementsWithout0Without1);

		heap3.insert(new TestObject("C"));
		heap3.insert(new TestObject("B"));
		heap3.delMin();
		assertEquals(heap3.findMin().getValue(), "C");

	}

	// Not tested that much, because if delMin is right, min should be too.
	@Test
	void testMin() {
		heap1 = new BinoHeap<Integer>();
		Integer min = heap1.min();
		assertEquals(min, null);
		assertEquals(heap1.toString(), TestStrings.empty);
		heap1.insert(0);
		min = heap1.min();
		assertEquals(min, 0);
		assertEquals(heap1.toString(), TestStrings.empty);
		heap1.insert(0);
		heap1.insert(-1);
		min = heap1.min();
		assertEquals(min, -1);
		assertEquals(heap1.toString(), TestStrings.heap1Element);
	}

	// Performance not tested
	@Test
	void testDecreaseKey() {
		heap1 = new BinoHeap<Integer>();
		heap1.decreaseKey(42, 0);
		assertEquals(heap1.toString(), TestStrings.empty);
		heap1.decreaseKey(42, 43);
		assertEquals(heap1.toString(), TestStrings.empty);
		heap1.insert(0);
		heap1.decreaseKey(42, 0);
		assertEquals(heap1.toString(), TestStrings.heap1Element);

		heap1.decreaseKey(0, -1);
		assertEquals(heap1.toString(), TestStrings.heapDecrease1Element);
		heap1.decreaseKey(-1, -2);
		heap1.insert(0);
		heap1.decreaseKey(0, -1);
		assertEquals(heap1.toString(), TestStrings.heapDecrease2Elements);

		heap1 = new BinoHeap<Integer>();
		for (int i = 0; i < 15; i++) {
			heap1.insert(i);
		}
		heap1.decreaseKey(15, -1);
		assertEquals(heap1.toString(), TestStrings.heap15Elements);
		heap1.decreaseKey(15, 16);
		assertEquals(heap1.toString(), TestStrings.heap15Elements);
		heap1.decreaseKey(7, -1);
		assertEquals(heap1.toString(), TestStrings.heapDecrease7);
		heap1.decreaseKey(6, -2);
		assertEquals(heap1.toString(), TestStrings.heapDecrease6);
		heap1.decreaseKey(6, 0);
		assertEquals(heap1.toString(), TestStrings.heapDecrease6);
		heap1.decreaseKey(13, 6);
		assertEquals(heap1.toString(), TestStrings.heapDecrease13);
		heap1.decreaseKey(14, 7);
		assertEquals(heap1.toString(), TestStrings.heapDecrease14);
		heap1.decreaseKey(-2, -3);
		assertEquals(heap1.toString(), TestStrings.heapDecreaseNegative2);

		// Not checked on edge cases: Just for an example!!
		heap3.insert(new TestObject("Alpha"));
		heap4.insert(new TestObject("Beta"));
		heap4.merge(heap3);
		String a = heap4.getRoots().get(0).getValue().getValue();
		String b = heap4.getRoots().get(0).getChild(0).getValue().getValue();
		assertEquals(a, "Alpha");
		assertEquals(b, "Beta");

	}

	@Test
	void testMerge() {
		resetAndfillHeaps();

		heap1.merge(null);
		assertEquals(heap1.toString(), TestStrings.heapMergeExampleArtemisNoChange);
		heap1.merge(new BinoHeap<>());
		assertEquals(heap1.toString(), TestStrings.heapMergeExampleArtemisNoChange);
		heap1.merge(heap2);
		assertEquals(heap1.toString(), TestStrings.heapMergeExampleArtemisMerged);
		BinoHeap<Integer> tmp = new BinoHeap<>();
		tmp.insert(0);
		heap1.insert(13);
		heap1.merge(tmp);
		assertEquals(heap1.toString(), TestStrings.heapMergeExampleOneElement);

		resetAndfillHeaps();
		heap2.insert(13);
		heap2.insert(15);
		heap1.merge(heap2);
		assertEquals(heap1.toString(), TestStrings.heapMergeExampleInnerMergeAfterOuterMerge);

		// Not checked on edge cases: Just for an example!!
		heap3 = new BinoHeap<>();
		heap3.insert(new TestObject("Alpha"));
		heap3.insert(new TestObject("Charlie"));
		heap4.insert(new TestObject("Beta"));
		heap4.insert(new TestObject("Delta"));
		heap3.merge(heap4);
		
		assertEquals(heap3.getRoots().get(0).getValue().getValue(), "Alpha");
		assertEquals(heap3.getRoots().get(0).getChild(0).getValue().getValue(), "Charlie");
		assertEquals(heap3.getRoots().get(0).getChild(1).getValue().getValue(), "Beta");
		assertEquals(heap3.getRoots().get(0).getChild(1).getChild(0).getValue().getValue(), "Delta");
		
	}

	private void resetAndfillHeaps() {
		heap1 = new BinoHeap<Integer>();
		heap2 = new BinoHeap<Integer>();

		heap1.insert(2);
		heap1.insert(8);
		heap1.insert(4);
		heap1.insert(5);
		heap1.insert(7);
		heap1.insert(10);
		heap1.insert(12);
		heap1.insert(21);
		heap1.insert(9);
		heap1.insert(11);
		heap1.insert(1);

		heap2.insert(6);
		heap2.insert(14);
		heap2.insert(3);
	}

}
