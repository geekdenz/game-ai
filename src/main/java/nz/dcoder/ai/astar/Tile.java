package nz.dcoder.ai.astar;

/**
 *
 * @author denz
 */
public class Tile implements Comparable<Tile> {
	public final int x;
	public final int y;

	public Tile(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(final Tile o) {
		int ox = o.x;
		int oy = o.y;
		return this.x == ox ? this.y - oy : this.x - ox;
	}

	@Override
	public int hashCode() {
		return x + y;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == null) return false;
		else if (!(o instanceof Tile)) return false;
		else {
			final Tile ot = (Tile) o;
			return this.x == ot.x && this.y == ot.y;
		}
	}
}

