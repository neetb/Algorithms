/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

	// compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
		@Override
		public int compare(Point o1, Point o2) {
			Point ref = new Point(x, y);
			double slopeOne = ref.slopeTo(o1);
			double slopeTwo = ref.slopeTo(o2);

			if (slopeOne < slopeTwo) {
				return -1;
			} else if (slopeOne > slopeTwo) {
				return 1;
			} else {
				return 0;
			}
		}
	};

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}

	// plot this point to standard drawing
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	public double slopeTo(Point that) {
		int x1 = this.x;
		int x2 = that.x;
		int y1 = this.y;
		int y2 = that.y;

		double deltaX = x2 - x1;
		double deltaY = y2 - y1;

		// Treat the slope of a degenerate line segment (between a point and itself) as negative infinity
		if ((deltaX == 0) && (deltaY == 0)) {
			return Double.NEGATIVE_INFINITY;
		}

		// Treat the slope of a vertical line segment as positive infinity.
		if (deltaX == 0) {
			return Double.POSITIVE_INFINITY;
		}

		// Treat the slope of a horizontal line segment as positive zero.
		if (deltaY == 0) {
			return Double.valueOf(0);
		}

		double slope = deltaY / deltaX;
		return slope;

	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that) {
		int y0 = this.y;
		int y1 = that.y;
		int x0 = this.x;
		int x1 = that.x;

		if (y0 == y1) {
			if (x0 == x1) {
				return 0;
			} else {
				return (x0 < x1 ? -1 : 1);
			}
		}

		if (y0 < y1) {
			return -1;
		} else {
			return 1;
		}

	}

	// return string representation of this point
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

	// unit test
	public static void main(String[] args) {
		/* YOUR CODE HERE */
	}
}