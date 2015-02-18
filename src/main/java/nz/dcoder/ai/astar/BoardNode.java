package nz.dcoder.ai.astar;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author denz
 */
public class BoardNode extends Node<Tile> {
	private final BoardState board;

	public BoardNode(Node<Tile> parent, BoardState board, Tile start, Tile goal) {
		super(parent, start, goal);
		this.board = board;
	}

	@Override
	public Node<Tile> getGoalNode() {
		Tile goal = this.getGoal();
		return new BoardNode(this.getParent(), this.board, goal, goal);
	}

	@Override
	public Set<Node<Tile>> getAdjacentNodes() {
		Set<Node<Tile>> nodes = new TreeSet<>();

		Tile pos = this.getPosition();
		Tile goal = this.getGoal();
		Tile n = new Tile(pos.x, pos.y - 1);
		Tile s = new Tile(pos.x, pos.y + 1);
		Tile e = new Tile(pos.x + 1, pos.y);
		Tile w = new Tile(pos.x - 1, pos.y);

		Node<Tile> parent = this.getParent();
		if (!(parent != null && parent.getPosition().equals(pos))) {
			if (board.contains(n)) nodes.add(new BoardNode(this, board, n, goal));
			if (board.contains(s)) nodes.add(new BoardNode(this, board, s, goal));
			if (board.contains(e)) nodes.add(new BoardNode(this, board, e, goal));
			if (board.contains(w)) nodes.add(new BoardNode(this, board, w, goal));
		}

		return nodes;
	}

	@Override
	public int compareTo(Node<Tile> other) {
		if (this.getCost() != other.getCost()) {
			return super.compareTo(other);

		} else {
			if (this.equals(other)) {
				return 0;

			} else {
				Tile n1 = this.getPosition();
				Tile n2 = other.getPosition();
				return (n1.x == n2.x) ? (n1.y - n2.y) : (n1.x - n2.x);
			}
		}
	}

	@Override
	protected double g() {
		Node<Tile> parent = getParent();
		if (parent == null) return 1.0; else return parent.g() + 1.0;
	}

	@Override
	protected double h(Tile goal) {
		Tile here = this.getPosition();
		double xDiff = goal.x - here.x;
		double yDiff = goal.y - here.y;
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}

	@Override
	public String toString() {
		Tile here = this.getPosition();
		return "(" + here.x + ", " + here.y + ")";
	}
}

