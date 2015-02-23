package nz.dcoder.ai.astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author denz
 */
public class AStarSearch<T> {
	private final Node<T> startNode;
	private final Node<T> goalNode;

	public AStarSearch(Node<T> startNode) {
		this.startNode = startNode;
		this.goalNode = startNode.getGoalNode();
	}

	public List<Node<T>> search() {

		List<Node<T>> nodeList = new ArrayList<>();
		SortedSet<Node<T>> open = new TreeSet<>();
		Set<Node<T>> closed = new HashSet<>();

		Node<T> current;
		open.add(startNode);
		Node<T> lastNode = null;

		while (!open.isEmpty()) {
			current = open.first();

			if (current.equals(goalNode)) {
				// path complete
				lastNode = current;
				break;

			} else {
				closed.add(current);
				open.remove(current);
				Set<Node<T>> adjacent = current.getAdjacentNodes();
				for (Node<T> node : adjacent) {
					if (!open.contains(node) && !closed.contains(node)) {
						// move it to the open list
						open.add(node);
					}
				}
			}
		}

		Node<T> parent;

		do {
			parent = lastNode.getParent();
			nodeList.add(lastNode);
			lastNode = parent;
		} while (parent != null);

		Collections.reverse(nodeList);

		return nodeList;
	}
}

