/**
 * @author Medetkan Kutlu 20170808068
 * @since 28.12.22
 * I did it myself
 */

import java.util.Arrays;
import java.util.Random;

public class Medetkan_Kutlu_Q1 {

    public static class Test {
        public static void main(String[] args) {


            //100,000 different integer
            int[] keys = new int[1000];
            for (int i = 0; i < 1000; i++) {
                keys[i] = i;
            }

            //Random frequency
            Random rand = new Random();
            int[] freq = new int[1000];
            for (int i = 0; i < 1000; i++) {
                freq[i] = rand.nextInt(100) + 1;
            }

            int n = keys.length;

            // Calculate the cost of an optimal BST
            Node[][] root = Optimal_BST2.optimalSearchTree(keys, freq, n);
            int optCost = root[0][n - 1].cost;

            //Two different bst with insert method
            BinarySearchTree bst1 = new BinarySearchTree();
            BinarySearchTree bst2 = new BinarySearchTree();
            for (int i = 0; i < 1000; i++) {
                int idx = rand.nextInt(1000);
                bst1.insert(keys[idx]);
                bst2.insert(keys[idx]);
            }

            //Optimal bst with constructOptimalSearchTree
            BinarySearchTree bst3 = new BinarySearchTree();
            bst3.root = BinarySearchTree.constructOptimalSearchTree(root);

            //100 Random search key
            int[] searchKeys = new int[100];
            for (int i = 0; i < 100; i++) {
                searchKeys[i] = rand.nextInt(1000);
            }

            //Average physical running time
            double bst1Time = 0;
            double bst2Time = 0;
            double bst3Time = 0;
            for (int i = 0; i < 100; i++) {
                long startTime = System.nanoTime();
                bst1.search(bst1.root, searchKeys[i]);
                long endTime = System.nanoTime();
                bst1Time += (endTime - startTime) * (freq[i] / 100.0);

                startTime = System.nanoTime();
                bst2.search(bst2.root, searchKeys[i]);
                endTime = System.nanoTime();
                bst2Time += (endTime - startTime) * (freq[i] / 100.0);

                startTime = System.nanoTime();
                bst3.search(bst3.root, searchKeys[i]);
                endTime = System.nanoTime();
                bst3Time += (endTime - startTime) * (freq[i] / 100.0);
            }

            //Results
            System.out.println("BST 1 Average Time: " + bst1Time);
            System.out.println("BST 2 Average Time: " + bst2Time);
            System.out.println("BST 3 (Optimal) Average Time: " + bst3Time);
            System.out.println("Optimal BST Cost: " + optCost);

        }
    }

    public static class Node {
        int key;
        int cost;
        int root;
        Node left, right;

        public Node(int key, int cost, int root) {
            this.key = key;
            this.cost = cost;
            this.root = root;
            left = null;
            right = null;
        }

        public Node(int key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "Key: " + key + " Cost: " + cost;
        }
    }

    public static class Optimal_BST2 {

        public static Node[][] optimalSearchTree(int[] keys, int[] freq, int n) {

		/* Create an auxiliary 2D matrix to store results of
		subproblems */

            int[][] cost = new int[n + 1][n + 1];
            Node[][] table = new Node[n + 1][n + 1];

		/* cost[i][j] = Optimal cost of binary search tree that
		can be formed from keys[i] to keys[j]. cost[0][n-1]
		will store the resultant cost */


            // Fill table with 0
            for (int i = 0, len = table.length; i < len; i++)
                Arrays.fill(table[i], new Node(0, 0, 0));


            // For a single key, cost is equal to frequency of the key
            for (int i = 0; i < n; i++) {
                cost[i][i] = freq[i];
                table[i][i] = new Node(keys[i], freq[i], i);
            }
            // Now we need to consider chains of length 2, 3, ... .
            // L is chain length.
            for (int L = 2; L <= n; L++) {

                // i is row number in cost[][]
                for (int i = 0; i <= n - L + 1; i++) {

                    // Get column number j from row number i and
                    // chain length L
                    int j = i + L - 1;
                    cost[i][j] = Integer.MAX_VALUE;
                    //table[i][j] = new KeyCost(keys[i], Integer.MAX_VALUE);
                    int off_set_sum = sum(freq, i, j);

                    // Try making all keys in interval keys[i…j] as root
                    for (int r = i; r <= j; r++) {

                        // c = cost when keys[r] becomes root of this subtree
                        int c = ((r > i) ? cost[i][r - 1] : 0)
                                + ((r < j) ? cost[r + 1][j] : 0) + off_set_sum;
                        if (c < cost[i][j]) {
                            cost[i][j] = c;
                            table[i][j] = new Node(keys[r], cost[i][j], r);

                        }
                    }
                }
            }
            return table;
        }

        // A utility function to get sum of array elements
        // freq[i] to freq[j]
        public static int sum(int[] freq, int i, int j) {
            int s = 0;
            for (int k = i; k <= j; k++) {
                if (k >= freq.length)
                    continue;
                s += freq[k];
            }
            return s;
        }
    }

    public static class BinarySearchTree {

        // Root of BST
        Node root;

        // Constructor
        BinarySearchTree() {
            root = null;
        }

        BinarySearchTree(int value) {
            root = new Node(value);
        }


        public static Node constructOptimalSearchTree(Node[][] matrix) {
            Node root = matrix[0][matrix.length - 2];

            // Get the root node
            int rootKey = root.root;

            root.left = constructOptimalSearchTree(matrix, 0, rootKey - 1);
            root.right = constructOptimalSearchTree(matrix, rootKey + 1, matrix.length - 2);

            return root;
        }

        // Helper for constructOptimalSearchTree
        public static Node constructOptimalSearchTree(Node[][] matrix, int start, int end) {
            //Base case
            if (start > end || start == matrix.length - 1 || end == matrix.length - 1)
                return null;

            //Get the root node
            Node root = matrix[start][end];
            int rootKey = root.root;

            root.left = constructOptimalSearchTree(matrix, start, rootKey - 1);
            root.right = constructOptimalSearchTree(matrix, rootKey + 1, end);

            return root;
        }


        // This method mainly calls insertRec()
        void insert(int key) {
            root = insertRec(root, key);
        }

        /* A recursive function to
           insert a new key in BST */
        Node insertRec(Node root, int key) {

        /* If the tree is empty,
           return a new node */
            if (root == null) {
                root = new Node(key);
                return root;
            }

            /* Otherwise, recur down the tree */
            else if (key < root.key)
                root.left = insertRec(root.left, key);
            else if (key > root.key)
                root.right = insertRec(root.right, key);

            /* return the (unchanged) node pointer */
            return root;
        }

        // This method mainly calls InorderRec()
        void inorder() {
            inorderRec(root);
        }

        // A utility function to
        // do inorder traversal of BST
        void inorderRec(Node root) {
            if (root != null) {
                inorderRec(root.left);
                System.out.println(root.key);
                inorderRec(root.right);
            }
        }

        public Node search(Node root, int key) {
            // Base Cases: root is null or key is present at root
            if (root == null || root.key == key)
                return root;

            // Key is greater than root's key
            if (root.key < key)
                return search(root.right, key);

            // Key is smaller than root's key
            return search(root.left, key);
        }
    }

}
