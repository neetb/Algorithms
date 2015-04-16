import java.util.ArrayList;
import java.util.List;


public class KdTree {
	private Node root;
	private int count;

	// is the set empty?
	public boolean isEmpty() {
		return root == null;
	}

	// number of points in the set
	public int size() {
		return count;
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		root = insertRecursive(root, p);
	}
	
	private Node insertRecursive(Node node, Point2D point){
		if(node == null)	{
			return new Node(point);
		}
		
		if(point.x() < node.point.x())	{
			node.left = insertRecursive(node.left, point);
		}else	{
			node.right = insertRecursive(node.right, point);
		}
		return node;
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

	
	private class Node	{
		Node left;
		Node right;
		Point2D point;
		
		public Node(Point2D point){
			this.point = point;
			this.left = null;
			this.right = null;
		}
		
		
		
	}
	
}
