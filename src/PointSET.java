import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {

	private Set<Point2D> points;

	// construct an empty set of points
	public PointSET() {
		points = new TreeSet<Point2D>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return points.isEmpty();
	}

	// number of points in the set
	public int size() {
		return points.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		points.add(p);
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		return points.contains(p);
	}

	// draw all points to standard draw
	public void draw() {
		for (Point2D p : points) {
			StdDraw.point(p.x(), p.y());
		}
	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		List<Point2D> result = new ArrayList<Point2D>();
		for (Point2D p : points) {
			if (rect.contains(p)) {
				result.add(p);
			}
		}
		return result;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		double minDistance = Double.MAX_VALUE;
		Point2D nearest = null;

		for (Point2D point : points) {
			double dist = point.distanceTo(p);
			if (dist < minDistance) {
				minDistance = dist;
				nearest = point;
			}
		}
		return nearest;
	}

	// unit testing of the methods (optional)
	public static void main(String[] args) {

	}
}
