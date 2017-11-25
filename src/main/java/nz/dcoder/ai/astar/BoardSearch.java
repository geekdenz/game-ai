package nz.dcoder.ai.astar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author denz
 */
public class BoardSearch {

	public static void main(final String args[]) {
		final Set<Node<Tile>> testSet = new TreeSet<>();
		final Tile goal = new Tile(3,3);
		System.out.println(testSet.add(new BoardNode(null, null, new Tile(1, 1), goal)));
		System.out.println(testSet.add(new BoardNode(null, null, new Tile(1, 2), goal)));
		System.out.println(testSet);

		final int board[][] = new int[][]{
			{0, 0, 0, 0},
			{0, 0, -1, 0},
			{0, 0, -1, 0},
			{0, 0, 0, 0}
		};

		// Test loading a board
		final BoardState boardState = new BoardState().load("board.map");
		System.out.println(boardState);

		// Test path finding
		final Node<Tile> start =
			new BoardNode(null, boardState, new Tile(0, 0), new Tile(9, 9));
		final AStarSearch<Tile> search = new AStarSearch<>(start);
		final List<Node<Tile>> path = search.search();
		System.out.println(path);

		// Print the path
		for (final Node<Tile> node : path) {
			final Tile p = node.getPosition();
			boardState.set(p.x, p.y, 9);
		}
		System.out.println(boardState);
	}
}

