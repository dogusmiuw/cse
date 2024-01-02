public class App {
  public static void main(String[] args) throws Exception {
    Node nodeA = new Node("A");
    Node nodeB = new Node("B");
    Node nodeC = new Node("C");
    Node nodeD = new Node("D");
    Node nodeE = new Node("E");
    Node nodeF = new Node("F");

    nodeA.addConnection(nodeB, 10);
    nodeA.addConnection(nodeC, 15);

    nodeB.addConnection(nodeD, 12);
    nodeB.addConnection(nodeF, 15);

    nodeC.addConnection(nodeE, 10);

    nodeD.addConnection(nodeE, 2);
    nodeD.addConnection(nodeF, 1);

    nodeF.addConnection(nodeE, 5);

    Graph graph = new Graph();
    graph.addNode(nodeA);
    graph.addNode(nodeB);
    graph.addNode(nodeC);
    graph.addNode(nodeD);
    graph.addNode(nodeE);
    graph.addNode(nodeF);

    Graph g = DjikstraAlgorithm.calculateShortestPath(graph, nodeA);

  }
}
