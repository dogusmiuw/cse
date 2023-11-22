import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Lab05_TreeTraversal {
    public static void main(String[] args) throws Exception {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n5 = new Node(5);
        n1.children.add(n2);
        n1.children.add(n5);

        Node n3 = new Node(3);
        n2.children.add(n3);

        Node n4 = new Node(4);
        n3.children.add(n4);

        Tree tree = new Tree(0);
        tree.root = n1;

        tree.bfs();
        tree.dfs();
    }

}

class Node {
    int value;
    List<Node> children;

    public Node(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
}

class Tree {
    Node root;

    public Tree(int value) {
        root = new Node(value);
    }

    public void bfs(){
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        while (!q.isEmpty()) {
            Node n = q.poll();
            System.out.println(n.value);
            for (Node child : n.children) {
                q.add(child);
            }
        }
    }

    private void dfs (Node n){
        if(n==null)
            return;

        System.out.println(n.value);

        for (Node child : n.children) {
            dfs(child);
        }
    }

    public void dfs(){
        dfs(this.root);
    }
}
