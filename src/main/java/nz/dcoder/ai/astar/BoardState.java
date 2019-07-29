package nz.dcoder.ai.astar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author informatics-palmerson
 */
public class BoardState implements Serializable {

	private int board[][];

	public BoardState() {
		this.board = new int[5][5];
		for (int x = 0; x < board.length; ++x) {
			for (int y = 0; y < board[x].length; ++y) {
				board[x][y] = 0;
			}
		}
	}

	public void set(final int x, final int y, final int value) {
		board[x][y] = value;
	}

	public int get(final int x, final int y) {
		return board[x][y];
	}

	public boolean contains(final Tile p) {
		return
			p.x >= 0 && p.x < this.getWidth() &&
			p.y >= 0 && p.y < this.getHeight() &&
			board[p.x][p.y] == 0;
	}

	public void randomise() {
		for (int x = 0; x < board.length; ++x) {
			for (int y = 0; y < board[x].length; ++y) {
				board[x][y] = (int) Math.round(Math.random());
			}
		}
	}

	public static void main(final String args[]) {
		final BoardState boardState = new BoardState();
		boardState.randomise();
		boardState.save();
		final BoardState loaded = new BoardState().load();
		final String bsWritten = boardState.toString();
		final String bsRead = loaded.toString();
		System.out.println(bsRead);
		System.out.println(bsRead.equals(bsWritten) ? "EQUAL" : "DIFF");

		final BoardState readState = new BoardState();
		readState.load("board.map");
	}

	public void save() {
		try (final FileOutputStream fileOut
				= new FileOutputStream("/tmp/boardState.ser");
				ObjectOutputStream out
				= new ObjectOutputStream(fileOut)) {
			out.writeObject(this);
			System.out.printf("Serialized data is saved in /tmp/boardState.ser");
		} catch (final IOException i) {
			throw new RuntimeException(i);
		}
	}

	public BoardState load() {
		try (final FileInputStream fileIn = new FileInputStream("/tmp/boardState.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			return (BoardState) in.readObject();
		} catch (final IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public BoardState load(final String mapFile) {
		final Path path = FileSystems.getDefault().getPath("", mapFile);

		List<String> lines = null;
		try {
			lines = Files.readAllLines(path, Charset.defaultCharset());
		} catch (final IOException ex) {
			Logger.getLogger(BoardState.class.getName()).log(Level.SEVERE, null, ex);
		}

		int y = 0;
		for (final String line : lines) {
			if (y == 0) {
				//board = new int[lines.size() / 2][line.length()];
				board = new int[line.length() / 2][lines.size()];
			}
			System.out.println(line);
			int len = line.length();
			for (int x = 0; x < len - 1; x += 2) {
				final char byte1 = line.charAt(x);
				final char byte2 = line.charAt(x + 1);
				final String hex = "" + byte1 + byte2;
				final int value = Integer.parseInt(hex, 16);
				board[x / 2][y] = value;
			}
			++y;
		}
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder ret = new StringBuilder();
		for (int y = 0; y < board[0].length; y++) {
			if (y > 0) {
				ret.append("\n");
			}
			for (int x = 0; x < board.length; x++) {
				if (x > 0) {
					ret.append(",");
				}
				ret.append(board[x][y]);
			}
		}
		return ret.toString();
	}

	public int getWidth() {
		return board.length;
	}

	public int getHeight() {
		return board.length > 0 ? board[0].length : 0;
	}

}
