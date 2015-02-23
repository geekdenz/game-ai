package nz.dcoder.ai.astar;

import javax.vecmath.Point2i;

/**
 *
 * @author denz
 */
public class Tile extends Point2i implements Comparable<Tile> {
	boolean isObstacle = false;
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Tile o) {
		int ox = o.x;
		int oy = o.y;
		return this.x == ox ? this.y - oy : this.x - ox;
	}
	public boolean equals(Tile o) {
		return this.x == o.x && this.y == o.y;
	}
}

