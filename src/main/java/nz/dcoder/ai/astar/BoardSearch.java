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

	public static void main(String args[]) {
		Set<Node<Tile>> testSet = new TreeSet<>();
		Tile goal = new Tile(3,3);
		System.out.println(testSet.add(new BoardNode(null, null, new Tile(1, 1), goal)));
		System.out.println(testSet.add(new BoardNode(null, null, new Tile(1, 2), goal)));
		System.out.println(testSet);

		int board[][] = new int[][]{
			{0, 0, 0, 0},
			{0, 0, -1, 0},
			{0, 0, -1, 0},
			{0, 0, 0, 0}
		};

		// Test loading a board
		BoardState boardState = new BoardState().load("board.map");
		System.out.println(boardState);

		// Test path finding
		Node<Tile> start =
			new BoardNode(null, boardState, new Tile(0, 0), new Tile(9, 9));
		AStarSearch search = new AStarSearch(start);
		List<Node<Tile>> path = search.search();
		System.out.println(path);

		// Print the path
		for (Node<Tile> node : path) {
			Tile p = node.getPosition();
			boardState.set(p.x, p.y, 9);
		}
		System.out.println(boardState);
	}
}
