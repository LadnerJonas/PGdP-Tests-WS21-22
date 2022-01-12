package pgdp.pingulib.datastructures.trees.heaps.binomial;

public class TestObject implements Comparable<TestObject> {

	String value;

	public TestObject(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int compareTo(TestObject o) {
		// Not checked on edge cases: Just for an example!!
		int diff = getValue().charAt(0) - o.getValue().charAt(0);
		return diff;
	}

}
