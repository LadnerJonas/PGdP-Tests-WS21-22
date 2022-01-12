package pgdp.pingulib.datastructures.trees.heaps.binomial;

public class TestStrings {
	
	public static final String empty = "min: -\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "}";
	public static final String heap1Element = "min: 0\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[0]\n"
			+ "}";
	public static final String heap15Elements = "min: 0\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[14]\n"
			+ "[12, [13]]\n"
			+ "[8, [9], [10, [11]]]\n"
			+ "[0, [1], [2, [3]], [4, [5], [6, [7]]]]\n"
			+ "}";
	public static final String heap15ElementsWithout0 = "min: 1\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[2, [3]]\n"
			+ "[4, [5], [6, [7]]]\n"
			+ "[1, [14], [12, [13]], [8, [9], [10, [11]]]]\n"
			+ "}";
	public static final String heap15ElementsWithout0Without1 = "min: 2\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[14]\n"
			+ "[8, [9], [10, [11]]]\n"
			+ "[2, [3], [12, [13]], [4, [5], [6, [7]]]]\n"
			+ "}";
	public static final String heap15ElementsAndNegative1 = "min: -1\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[-1, [14], [12, [13]], [8, [9], [10, [11]]], [0, [1], [2, [3]], [4, [5], [6, [7]]]]]\n"
			+ "}";
	public static final String heapDecrease1Element = "min: -1\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[-1]\n"
			+ "}";
	public static final String heapDecrease2Elements = "min: -2\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[-2, [-1]]\n"
			+ "}";
	public static final String heapDecrease7 = "min: -1\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[14]\n"
			+ "[12, [13]]\n"
			+ "[8, [9], [10, [11]]]\n"
			+ "[-1, [1], [2, [3]], [0, [5], [4, [6]]]]\n"
			+ "}";
	public static final String heapDecrease6 = "min: -2\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[14]\n"
			+ "[12, [13]]\n"
			+ "[8, [9], [10, [11]]]\n"
			+ "[-2, [1], [2, [3]], [-1, [5], [0, [4]]]]\n"
			+ "}";
	public static final String heapDecrease13 = "min: -2\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[14]\n"
			+ "[6, [12]]\n"
			+ "[8, [9], [10, [11]]]\n"
			+ "[-2, [1], [2, [3]], [-1, [5], [0, [4]]]]\n"
			+ "}";
	public static final String heapDecrease14 = "min: -2\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[7]\n"
			+ "[6, [12]]\n"
			+ "[8, [9], [10, [11]]]\n"
			+ "[-2, [1], [2, [3]], [-1, [5], [0, [4]]]]\n"
			+ "}";
	public static final String heapDecreaseNegative2 = "min: -3\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[7]\n"
			+ "[6, [12]]\n"
			+ "[8, [9], [10, [11]]]\n"
			+ "[-3, [1], [2, [3]], [-1, [5], [0, [4]]]]\n"
			+ "}";
	public static final String heapMergeExampleArtemisNoChange = "min: 1\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[1]\n"
			+ "[9, [11]]\n"
			+ "[2, [8], [4, [5]], [7, [10], [12, [21]]]]\n"
			+ "}";
	public static final String heapMergeExampleArtemisMerged = "min: 1\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[1, [3]]\n"
			+ "[6, [14], [9, [11]]]\n"
			+ "[2, [8], [4, [5]], [7, [10], [12, [21]]]]\n"
			+ "}";
	public static final String heapMergeExampleOneElement = "min: 0\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[0, [13], [1, [3]], [6, [14], [9, [11]]], [2, [8], [4, [5]], [7, [10], [12, [21]]]]]\n"
			+ "}";
	public static final String heapMergeExampleInnerMergeAfterOuterMerge = "min: 1\n"
			+ "rootlist:\n"
			+ "{\n"
			+ "[1, [15], [9, [11]], [3, [13], [6, [14]]], [2, [8], [4, [5]], [7, [10], [12, [21]]]]]\n"
			+ "}";

}
