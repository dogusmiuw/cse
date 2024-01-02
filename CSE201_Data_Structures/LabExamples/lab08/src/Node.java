import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
	private String name;
	private Map<Node, Integer> connections;
	private int shortestDistance;
	private List<Node> shortestPath;

	public Node(String name) {
		this.name = name;
		this.connections = new HashMap<>();
		this.shortestDistance = Integer.MAX_VALUE;
		this.shortestPath = new ArrayList<Node>();
	}

	public void addConnection(Node node, int distance) {
		this.connections.put(node, distance);
	}

	// getters
	public String getName() {
		return name;
	}
	public int getShortestDistance() {
		return shortestDistance;
	}
	public Map<Node, Integer> getConnections() {
		return connections;
	}
	public List<Node> getShortestPath() {
		return shortestPath;
	}

	//setters
	public void setConnections(Map<Node, Integer> connections) {
		this.connections = connections;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setShortestDistance(int shortestDistance) {
		this.shortestDistance = shortestDistance;
	}
	public void setShortestPath(List<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}
}
