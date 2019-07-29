package nz.dcoder.ai.astar;

import java.util.Set;

/**
 *
 * @author denz
 */
public abstract class Node<T> implements Comparable<Node<T>> {
	private final Node<T> parent;
	private final T start;
	private final T goal;
	private final double cost;

	public Node(final Node<T> parent, final T start, final T goal) {
		this.parent = parent;
		this.start = start;
		this.goal = goal;
		this.cost = g() + h(goal);
	}

	public abstract Node<T> getGoalNode();

	public abstract double g();
	public abstract double h(final T goal);

	public abstract Set<Node<T>> getAdjacentNodes();

	@Override
	public boolean equals(final Object obj) {
		try {
			return ((Node) obj).getPosition().equals(this.start);
		} catch (final ClassCastException e) {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return start.hashCode();
	}

	@Override
	public int compareTo(final Node<T> other) {
		if (this.cost == other.getCost()) return 0;
		else if (this.cost < other.getCost()) return -1;
		else return 1;
	}

	public double getCost() {
		return this.cost;
	}

	/**
	 * @return the parent
	 */
	public Node<T> getParent() {
		return this.parent;
	}

	public T getPosition() {
		return this.start;
	}

	public T getGoal() {
		return this.goal;
	}
}

