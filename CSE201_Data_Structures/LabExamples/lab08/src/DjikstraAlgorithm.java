import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;

public class DjikstraAlgorithm {

	public static Graph calculateShortestPath(Graph graph, Node source) {

		source.setShortestDistance(0); // starting point

		PriorityQueue<Node> unvisitedNodes = new PriorityQueue<>(Comparator.comparingInt(n -> n.getShortestDistance()));
		Set<Node> visitedNodes = new HashSet<>();

		unvisitedNodes.add(source);

		while (!unvisitedNodes.isEmpty()) {
			Node currentNode = unvisitedNodes.poll();

			for (Entry<Node, Integer> connection : currentNode.getConnections().entrySet()) {
				Node adjNode = connection.getKey();
				int distance = connection.getValue();

				if (!visitedNodes.contains(adjNode)) {
					// calc min distance
					unvisitedNodes.add(adjNode);
					calculateDistance(currentNode, adjNode, distance);
				}
			}

			visitedNodes.add(currentNode);
		}

		return graph;
	}

	public static void calculateDistance(
			Node source,
			Node destination,
			int distance) {

		int newDistance = source.getShortestDistance() + distance;
		if (newDistance < destination.getShortestDistance()) {
			destination.setShortestDistance(newDistance);

			//
		}

	}
}
