public class RectHV {

	private double xmin;
	private double ymin;
	private double xmax;
	private double ymax;

	// construct the rectangle [xmin, xmax] x [ymin, ymax]
	public RectHV(double xmin, double ymin, double xmax, double ymax) {
		// throw a java.lang.IllegalArgumentException if (xmin > xmax) or (ymin
		// > ymax)
		if ((xmin > xmax) || (ymin > ymax)) {
			throw new IllegalArgumentException();
		}
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;

	}

	// minimum x-coordinate of rectangle
	public double xmin() {
		return this.xmin;
	}

	// minimum y-coordinate of rectangle
	public double ymin() {
		return this.ymin;
	}

	// maximum x-coordinate of rectangle
	public double xmax() {
		return this.xmax;
	}

	// maximum y-coordinate of rectangle
	public double ymax() {
		return this.ymax;
	}

	// does this rectangle contain the point p (either inside or on boundary)?
	public boolean contains(Point2D p) {
		double x = p.x();
		double y = p.y();
		return ((xmin < x) && (x < xmax) && (ymin < y) && (y < ymax));
	}

	// does this rectangle intersect that rectangle (at one or more points)?
	public boolean intersects(RectHV that) {
		return ((this.contains(new Point2D(that.xmin, that.ymin)))
				|| (this.contains(new Point2D(that.xmin, that.ymax)))
				|| (this.contains(new Point2D(that.xmax, that.ymin))) || (this
					.contains(new Point2D(that.xmax, that.ymax))));
	}

	// Euclidean distance from point p to closest point in rectangle
	public double distanceTo(Point2D p) {
		if (contains(p)) {
			return 0;
		}
		double x = p.x();
		double y = p.y();

		if ((xmin < x) && (xmax > x)) {
			return Math.abs(y - ymax);
		}

		if ((ymin < y) && (ymax > y)) {
			return Math.abs(x - xmax);
		}

		if ((x < xmin) && (y > ymax)) {
			return distance(x, y, xmin, ymax);
		}

		if ((x < xmin) && (y < ymin)) {
			return distance(x, y, xmin, ymin);
		}

		if ((x > xmax) && (y > ymax)) {
			return distance(x, y, xmax, ymax);
		}

		if ((x > xmax) && (y < ymin)) {
			return distance(x, y, xmax, ymin);
		}
		return 0;

	}

	private double distance(double x1, double y1, double x2, double y2) {
		return Math
				.pow(Math.pow(Math.abs(x2 - x1), 2)
						+ Math.pow(Math.abs(y2 - y1), 2), 0.5);
	}

	// square of Euclidean distance from point p to closest point in rectangle
	public double distanceSquaredTo(Point2D p) {
		return Math.pow(distanceTo(p), 2);
	}

	// does this rectangle equal that object?
	public boolean equals(Object that) {
		if (that == null)
			return false;

		if (!(that instanceof RectHV))
			return false;

		if (this == that)
			return true;

		RectHV thatRect = (RectHV) that;
		return ((thatRect.xmin == this.xmin) && (thatRect.ymin == this.ymin)
				&& (thatRect.xmax == this.xmax) && (thatRect.ymax == this.ymax));

	}

	// draw to standard draw
	public void draw() {

	}

	// string representation
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("sds");
		return str.toString();
	}
}
