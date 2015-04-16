import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Brute {

	private int pointsCount = 0;
	private Point[] input;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Invalid input - Input filename expected");
			return;
		}

		Brute brute = new Brute();
		try {
			brute.processInput(args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found " + e.getMessage());
		}

		//long start = System.currentTimeMillis();  
		brute.findLines();
		//long end = System.currentTimeMillis();
		//System.out.println("Done in time " + (end-start));
	}

	private void findLines() {
		for (int i = 0; i < pointsCount; i++) {
			for (int j = i + 1; j < pointsCount; j++) {
				for (int k = j + 1; k < pointsCount; k++) {
					for (int q = k + 1; q < pointsCount; q++) {
						if(isCollinear(input[i], input[j], input[k], input[q])){
							
							//Comparable[] items = new Comparable[4];
							List<Point> items = Arrays.asList(input[i], input[j], input[k], input[q]);
							Point[] points = items.toArray(new Point[0]);
							Arrays.sort(points);
							System.out.println(points[0] + "->" + points[1] + "->" + points[2] + "->" + points[3]);
							StdDraw.setXscale(0, 32768);
							StdDraw.setYscale(0, 32768);  
							points[0].drawTo(points[3]);
							//points[1].drawTo(points[2]);
							//points[2].drawTo(points[3]);
						}   
					}
				}
			}  
		}
	}

	private boolean isCollinear(Point p1, Point p2, Point p3, Point p4) {
		double slope1 = (p1.slopeTo(p2));
		double slope2 = (p1.slopeTo(p3));
		double slope3 = (p1.slopeTo(p4));

		if ((slope1) == (slope2) && ((slope1) == (slope3))) {
			return true;
		}

		return false;
	}

	private void processInput(String fileName) throws FileNotFoundException {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);  
		  
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
