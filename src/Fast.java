import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fast {
	private int pointsCount = 0;
	private Point[] input;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Invalid input - Input filename expected");
			return;
		}

		Fast fast = new Fast();
		try {
			fast.processInput(args[0]);

		} catch (FileNotFoundException e) {
			System.out.println("Input file not found " + e.getMessage());
		}

		fast.findLines();
	}

	private void findLines() {
		Map<Double, List<Point>> slopes = new HashMap<Double, List<Point>>();
		for (int i = 0; i < pointsCount - 3; i++) {

			// set origin
			Point origin = input[i];

			Point[] copy = Arrays.copyOfRange(input, i + 1, input.length);

			// sort the rest of the array
			Arrays.sort(copy, origin.SLOPE_ORDER);

			List<Point> collinearPoints = new ArrayList<Point>();

			double m1 = copy[0].slopeTo(origin);
			collinearPoints.add(copy[0]);

			for (int j = 1; j < copy.length; j++) {
				double m2 = copy[j].slopeTo(origin);
				if (m1 == m2) {
					List<Point> entry = slopes.get(m1);
					if ((entry != null) && (entry.contains(copy[j]))) {
						// ignore
					} else {
						collinearPoints.add(copy[j]);
					}
				} else {
					if (collinearPoints.size() >= 3) {
						if (slopes.get(m1) == null) {
							slopes.put(m1, copy(collinearPoints));
						} else {
							List<Point> existingPoints = slopes.get(m1);
							existingPoints.addAll(copy(collinearPoints));
						}

						print(origin, collinearPoints);
					}
					collinearPoints.clear();
					m1 = m2;
					collinearPoints.add(copy[j]);
				}
			}

			if (collinearPoints.size() >= 3) {
				if (slopes.get(m1) == null) {
					slopes.put(m1, copy(collinearPoints));
				} else {
					List<Point> existingPoints = slopes.get(m1);
					existingPoints.addAll(copy(collinearPoints));
				}
				print(origin, collinearPoints);
			}

		}

	}

	private List<Point> copy(List<Point> input) {
		if ((input == null) || (input.isEmpty()))
			return input;
		List<Point> response = new ArrayList<Point>();

		for (Point point : input) {
			response.add(point);
		}
		return response;
	}

	private void print(Point origin, List<Point> points) {
		if ((points == null) || (points.isEmpty())) {
			return;
		}
		points.add(origin);

		Point[] pointsArr = points.toArray(new Point[0]);
		Arrays.sort(pointsArr);

		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);

		int count = 0;
		for (Point p : pointsArr) {
			System.out.print(p);
			if (++count < pointsArr.length)
				System.out.print(" -> ");
		}

		Point last = pointsArr[pointsArr.length - 1];
		pointsArr[0].drawTo(last);

		System.out.println();
	}

	private void processInput(String fileName) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		try {
			try {
				boolean firstLine = true;
				int i = 0;
				String inputStr = null;
				while ((inputStr = reader.readLine()) != null) {
					if (firstLine) {
						this.pointsCount = Integer.parseInt(inputStr.trim());
						input = new Point[this.pointsCount];
						firstLine = false;
					} else {
						inputStr = inputStr.trim().replaceAll("\\s+", " ");
						String[] pair = inputStr.trim().split(" ");
						if ((pair == null) || (pair.length != 2)) {
							continue;
						}

						Point p = new Point(Integer.parseInt(pair[0].trim()),
								Integer.parseInt(pair[1].trim()));
						p.draw();
						input[i++] = p;

					}
				}
			} finally {
				reader.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
