/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
		Set<Node> testSet = new TreeSet<>();
		System.out.println(testSet.add(new BoardNode(1, 1, null)));
		System.out.println(testSet.add(new BoardNode(1, 1, null)));
		System.out.println(testSet);
		int board[][] = new int[][]{
			{0, 0, 0, 0},
			{0, 0, -1, 0},
			{0, 0, -1, 0},
			{0, 0, 0, 0}
		};
		/*
		 for (int i = 0; i < board.length; ++i) {
		 for (int j = 0; j < board[i].length; ++j) {
		 board[i][j] = 0;
		 }
		 }
		 */
		/*
		 int leni = board[0].length;
		 int lenj = board.length;
		 BoardNode.board = new int[leni][lenj];
		 for (int i = 0; i < leni; ++i) {
		 for (int j = 0; j < lenj; ++j) {
		 BoardNode.board[j][i] = board[i][j];
		 }
		 }
		 */
		//BoardNode.board = board;


		SortedSet<Tile> tiles = new TreeSet<>();
		tiles.add(new Tile(0, 0));
		tiles.add(new Tile(1, 0));
		tiles.add(new Tile(2, 0));
		tiles.add(new Tile(3, 0));
		tiles.add(new Tile(0, 1));
		tiles.add(new Tile(1, 1));
		tiles.add(new Tile(3, 1));
		tiles.add(new Tile(0, 2));
		tiles.add(new Tile(1, 2));
		tiles.add(new Tile(3, 2));
		tiles.add(new Tile(0, 3));
		tiles.add(new Tile(1, 3));
		tiles.add(new Tile(2, 3));
		tiles.add(new Tile(3, 3));
		BoardNode.tiles = tiles;
		Node start = new BoardNode(0, 2, null);
		Node goal = new BoardNode(3, 2, null);
		AStarSearch search = new AStarSearch(start, goal);
		List<Node> path = search.search();
		System.out.println(path);

		BoardState boardState = new BoardState().load("board.map");
		System.out.println(boardState);
		SortedSet<Tile> boardTiles = new TreeSet();
		int width = boardState.getWidth();
		int height = boardState.getHeight();
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				if (boardState.get(x, y) == 0) {
					boardTiles.add(new Tile(x, y));
				}
			}
		}
		BoardNode.tiles = boardTiles;

		start = new BoardNode(0, 0, null);
		goal = new BoardNode(9, 9, null);
		search = new AStarSearch(start, goal);
		path = search.search();
		System.out.println(path);

		for (Node node : path) {
			BoardNode bn = (BoardNode) node;
			int x = bn.getX();
			int y = bn.getY();
			boardState.set(x, y, 9);
		}
		System.out.println(boardState);
	}
}
