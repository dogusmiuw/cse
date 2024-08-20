package Algorithms.DynamicProgramming.BottomUp;

public class OptimalStringBinarySearchTree {

    private record OptimumBST(int[][] costTable, int[][] rootTable, int minCost, BST<?> bst) {
        public static void print(OptimumBST optimumBST) {

            System.out.println("*******    Minimum cost of optimal binary search tree  =  " + optimumBST.minCost + "    *******");

            System.out.println("*******    Cost Table is     *******");
            printArray(optimumBST.costTable);

            System.out.println("*******    Root Table is     *******");
            printArray(optimumBST.rootTable);

            System.out.println("*******    Optimal Binary Search Tree is     *******");
            optimumBST.bst.printTree();

            System.out.println("*******    Optimal Binary Search Tree Pre Order is     *******");
            optimumBST.bst.printPreOrder();

        }

    }
    private static class BST <T extends Comparable<T>> {

        Node root;

        private class Node {
            T key;
            Node left;
            Node right;

            public Node(T key) {
                this.key = key;
            }

        }

        public BST() {
            this.root = null;
        }

        public void insert(T key) {
            this.root = insert(this.root, key);
        }

        private Node insert(Node node, T key) {

            if (node == null)
                return new Node(key);

            if (key.compareTo(node.key) < 0)
                node.left = insert(node.left, key);
            else if (key.compareTo(node.key) > 0)
                node.right = insert(node.right, key);

            return node;

        }



        public void printTree () {
            printTree(this.root, 0);
        }

        private void printTree(Node node, int level) {

            if (node == null)
                return;

            printTree(node.right, level + 1);

            if (level != 0) {

                for (int i = 0; i < level - 1; i++)
                    System.out.print("|\t");

                System.out.println("|-------" + node.key);

            } else
                System.out.println(node.key);

            printTree(node.left, level + 1);

        }

        public void printInOrder() {
            printInOrder(this.root);
            System.out.println();
        }

        private void printInOrder(Node node) {

            if (node == null)
                return;

            printInOrder(node.left);
            System.out.print(node.key + " ");
            printInOrder(node.right);

        }

        public void printPreOrder() {
            printPreOrder(this.root);
            System.out.println();
        }

        private void printPreOrder(Node node) {

            if (node == null)
                return;

            System.out.print(node.key + " ");
            printPreOrder(node.left);
            printPreOrder(node.right);

        }

        public void printPostOrder() {
            printPostOrder(this.root);
            System.out.println();
        }

        private void printPostOrder(Node node) {

            if (node == null)
                return;

            printPostOrder(node.left);
            printPostOrder(node.right);
            System.out.print(node.key + " ");

        }

    }


    public static void main(String[] args) {


        String[] keys = { "Begin" , "Do" , "Else" , "End" , "If" , "Then" , "While" };
        int [] frequencies = {5, 40, 8, 4, 10, 10, 23};

        OptimumBST optimumBST = getOptimumBST(keys, frequencies);

        OptimumBST.print(optimumBST);


    }

    public static OptimumBST getOptimumBST(String[] keys, int[] frequencies) {

        int n = keys.length;

        int[][] costs = new int[n][n];
        int[][] roots = new int[n][n];

        for (int i = 0; i < n; i++) {
            costs[i][i] = frequencies[i];
            roots[i][i] = i;
        }

        for (int l = 2; l <= n; l++) {

            for (int i = 0; i <= n - l; i++) {

                int j = i + l - 1;

                costs[i][j] = Integer.MAX_VALUE;

                int sum = sum(frequencies, i, j);

                for (int r = i; r <= j; r++) {

                    int c = ((r > i) ? costs[i][r - 1] : 0) +
                            ((r < j) ? costs[r + 1][j] : 0) +
                            sum;

                    if (c < costs[i][j]) {
                        costs[i][j] = c;
                        roots[i][j] = r;
                    }

                }


            }

        }

        int minCost = costs[0][n - 1];

        BST<String> bst = new BST<>();

        fillBST(roots, 0, keys.length - 1, bst, keys);

        return new OptimumBST(costs, roots, minCost, bst);

    }



    // print optimal binary search tree method

    private static int sum(int[] frequencies, int i, int j) {

        int sum = 0;

        for (int k = i; k <= j; k++)
            sum += frequencies[k];

        return sum;

    }

    private static void fillBST(int[][] rootTable, int i, int j, BST<String> bst, String[] keys) {

        if (i > j)
            return;

        if (i == j)
            //System.out.println("d" + (i + 1) + " is the root");
            bst.insert(keys[i]);

        int r = rootTable[i][j];

        bst.insert(keys[r]);

        //System.out.println("k" + (r + 1) + " is the root");

        fillBST(rootTable, i, r - 1, bst, keys);

        fillBST(rootTable, r + 1, j, bst, keys);

    }

    private static void printArray(int[][] array) {

        for (int[] row : array) {

            for (int number : row)
                System.out.print(number + " ");

            System.out.println();

        }

    }

}