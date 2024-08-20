import java.util.*;


public class Hw2_Assignment {

	public static void main(String[] args) {
		System.out.print("Enter the size value : ");
		Scanner sc=new Scanner(System.in);
		int lenght=sc.nextInt();
		sc.close();
		int[] WN = RandomNodeWeights(lenght);
		System.out.println("\n----- Node Weights Array -----\n");
		System.out.println(Arrays.toString(WN) + "\n");
		
		int[][] WE = RandomEdgeWeights(lenght);
		System.out.println("----- Node Edges Array -----\n");
		for (int i = 0; i < WE.length; i++) {
			System.out.println(Arrays.toString(WE[i]));
		}
		System.out.println("\n");
		
		int greedyResult=greedyApproach(WN, WE);

		int recursiveResult=recursive(WE, WN, WN.length);

		dynamicProgram(WE, WN, WN.length);
		
		if(greedyResult==recursiveResult) {
			System.out.println("\n\nGreedy algorithms have followed a successful path with the given input values.");
		}else {
			System.out.println("\n\nGreedy algorithms have followed a failed path with the given input values.");
		}
		
		System.out.println("\n-------------------------------\n");
		
		long[][] times=new long[3][2];
		
		
		System.out.println("Array lenght : 10");
		counter=0;
		WN = RandomNodeWeights(10);
		WE = RandomEdgeWeights(10);
		long start=System.nanoTime();
		recursive(WE, WN, 10);
		long end =System.nanoTime()-start;
		System.out.println("\tRecursive :"+end/1000+"ms\n");
		times[0][0]=end/100;
		start=System.nanoTime();
		dynamicProgram(WE, WN, 10);
		end =System.nanoTime()-start;
		System.out.println("\tDynamic Programming :"+end/1000+"ms\n");
		times[0][1]=end/100;
		
		System.out.println("\nArray lenght : 100");
		counter=0;
		WN = RandomNodeWeights(100);
		WE = RandomEdgeWeights(100);
		start=System.nanoTime();
		recursive(WE, WN, 100);
		end =System.nanoTime()-start;
		System.out.println("\tRecursive :"+end/1000+"ms\n");
		times[1][0]=end/100;
		start=System.nanoTime();
		dynamicProgram(WE, WN, 100);
		end =System.nanoTime()-start;
		System.out.println("\tDynamic Programming :"+end/1000+"ms\n");
		times[1][1]=end/100;
		
		System.out.println("\nArray lenght : 1000");
		counter=0;
		WN = RandomNodeWeights(1000);
		WE = RandomEdgeWeights(1000);
		start=System.nanoTime();
		recursive(WE, WN, 1000);
		end =System.nanoTime()-start;
		System.out.println("\tRecursive :"+end/1000+"ms\n");
		times[2][0]=end/100;
		start=System.nanoTime();
		dynamicProgram(WE, WN, 1000);
		end =System.nanoTime()-start;
		System.out.println("\tDynamic Programming :"+end/1000+"ms\n");
		times[2][1]=end/100;
		
		System.out.println("\n--- *** 2x3 Table Involving The Actual Running Times *** ---\n");
		
		System.out.println("\t\t"+Arrays.toString(new String[] {"Recursive","Dynamic Programming"}));
		int a=10;
		for (long[] ls : times) {
			System.out.println("Size : "+a+" -> \t"+Arrays.toString(ls));
			a*=10;
		}
	}
	
	/************************* FILL ARRAYS *************************/

	public static int[] RandomNodeWeights(int input) {
		int WeightNode[] = new int[input];
		Random rnd = new Random();
		for (int i = 0; i < input; i++) {
			int NodeRand = rnd.nextInt(20) + 1;
			WeightNode[i] = NodeRand;
		}
		return WeightNode;
	}

	public static int[][] RandomEdgeWeights(int input) {
		int WeightEdge[][] = new int[input][input];
		Random rnd = new Random();
		int j = 0;

		while (2 * j + 1 <= WeightEdge.length - 1) {
			int LRand = rnd.nextInt(20) + 1;
			WeightEdge[j][2 * j + 1] = LRand;

			j++;
		}
		j = 0;
		while (2 * j + 2 <= WeightEdge.length - 1) {
			int RRand = rnd.nextInt(20) + 1;
			WeightEdge[j][2 * j + 2] = RRand;

			j++;
		}
		return WeightEdge;
	}

	/************************* GREEDY ALGORITHM *************************/

	public static int greedyApproach(int[] WN, int[][] WE) {
		Tree t = new Tree();
		t.root = t.add(WN, t.root, 0);

		int total = t.root.value;
		String description = "0";
		int i = 0;
		while (t.root.left != null && t.root.right != null) {
			if (WE[i][2 * i + 1] < WE[i][2 * i + 2]) {
				total += WE[i][2 * i + 1];
				total += t.root.left.value;
				i = 2 * i + 1;
				description += " " + i + " ";
				t.root = t.root.left;
			} else {
				total += WE[i][2 * i + 2];
				total += t.root.right.value;
				i = 2 * i + 2;
				description += " " + i + " ";
				t.root = t.root.right;
			}
		}
		description = description.replace("  ", " ");
		System.out.println("Greedy Algorithm : Min total weight path includes nodes " + description
				+ "with total weight " + total + ".");
		return total;
	}

	/************************* RECURSIVE *************************/

	public static int recursive(int[][] WE, int[] WN, int size) {

		int[] arr = new int[size];
		arr[0] = 0 + WN[0];
		int count = 1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (WE[i][j] != 0) {
					arr[count] = WE[i][j] + WN[j];
					count++;
				}
			}
		}

		Tree t = new Tree();
		t.root = t.add(arr, t.root, 0);
		int minValue=savePath(t);
		return minValue;

	}

	private static int savePath(Tree t) {
		String path = "";
		String ind = "";
		ArrayList<String> paths = new ArrayList<>();
		ArrayList<String> index = new ArrayList<>();
		pathFinder(t.root, paths, index, path, ind);

		int[] comp = new int[paths.size()];
		int total = 0, j = 0;
		for (String string : paths) {

			String[] arr = string.split("->");
			for (int i = 0; i < arr.length; i++) {
				total += Integer.parseInt(arr[i]);
			}
			comp[j] = total;
			j++;
			total = 0;
		}

		int minValue = comp[0];
		int temp = 0;
		for (int i = 1; i < comp.length; i++) {
			if (comp[i] < minValue) {
				minValue = comp[i];
				temp = i;
			}
		}
		String a = index.get(temp).replace("->", " ");
		System.out.println("Recursive Algorithm : Min total weight path includes nodes " + a + " with total weight "
				+ minValue + ".");
		return minValue;
	}

	private static void pathFinder(Tree.Node root, ArrayList<String> list, ArrayList<String> index, String s,
			String stringIndex) {

		if (root == null) {
			return;
		}
		s += "->" + root.value;
		stringIndex += "->" + root.index;
		if (root.left == null && root.right == null) {
			list.add(s.substring(2));
			index.add(stringIndex.substring(2));
			return;
		}

		if (root.left != null) {
			pathFinder(root.left, list, index, s, stringIndex);
		}
		if (root.right != null) {
			pathFinder(root.right, list, index, s, stringIndex);
		}
	}

	/************************* DYNAMIC PROGRAMMING *************************/

	private static int[] dpSum = new int[10];
	private static String[] dpIndex = new String[10];
	private static int counter = 0;

	public static void dynamicProgram(int[][] WE, int[] WN, int size) {

		int[] arr = new int[size];
		arr[0] = 0 + WN[0];
		int count = 1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (WE[i][j] != 0) {
					arr[count] = WE[i][j] + WN[j];
					count++;
				}
			}
		}

		Tree t = new Tree();
		t.root = t.add(arr, t.root, 0);
		dpSum = new int[t.getLeafCount(t.root) + 1];
		dpIndex = new String[t.getLeafCount(t.root) + 1];
		for (int i = 0; i < dpIndex.length; i++) {
			dpIndex[i] = "";
		}
		dfs(t);
	}

	private static void dfs(Tree t) {

		findPath(t.root, 0, "");

		int minValue = dpSum[0];
		int temp = 0;
		for (int i = 1; i < dpSum.length - 1; i++) {
			if (dpSum[i] < minValue) {
				minValue = dpSum[i];
				temp = i;
			}
		}

		System.out.println("Dynamic Programming Algorithm : Min total weight path includes nodes " + dpIndex[temp]
				+ "with total weight " + minValue + ".");

	}

	private static void findPath(Tree.Node root, int value, String indis) {
		if (root == null) {
			return;
		}

		dpSum[counter] += root.value;
		dpIndex[counter] += root.index + " ";
		value = root.value;
		indis = root.index + " ";
		if (root.left == null && root.right == null) {
			counter++;
			dpSum[counter] = dpSum[counter - 1] - value;
			dpIndex[counter] = dpIndex[counter - 1].replace(indis, "");
			return;
		}

		if (root.left != null) {
			findPath(root.left, value, indis);
		}
		if (root.right != null) {
			findPath(root.right, value, indis);
			dpSum[counter] = dpSum[counter] - value;
			dpIndex[counter] = dpIndex[counter].replace(indis, "");
		}else {
			dpSum[counter] = dpSum[counter] - value;
			dpIndex[counter] = dpIndex[counter].replace(indis, "");
		}
	}

}

/************************* CREATE TREE DATA STRUCTURE *************************/

class Tree {
	Node root;
	public static int counter = 0;

	static class Node {
		int value;
		Node left, right;
		int index;

		Node(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}

	public Node add(int[] array, Node root, int length) {
		if (length < array.length) {
			counter = length;

			Node temp = new Node(array[length], counter);
			root = temp;

			root.left = add(array, root.left, (2 * length + 1));

			root.right = add(array, root.right, (2 * length + 2));
		}
		return root;
	}

	public int getLeafCount(Node node) {
		if (node == null)
			return 0;
		if (node.left == null && node.right == null)
			return 1;
		else
			return getLeafCount(node.left) + getLeafCount(node.right);
	}
}