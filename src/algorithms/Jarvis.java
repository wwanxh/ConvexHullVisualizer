package algorithms;

import java.util.*;

import Model.Point;
import View.FramesViewer;
import View.Single_Frame;

public class Jarvis {
	
	

	// To find orientation of ordered triplet (p, q, r).
	public static boolean isLeftTurn(Point p, Point q, Point r) {
		// TODO Report arc that is being determined 
		int slope1 = (q.y - p.y) * (r.x - q.x);
		int slope2 = (q.x - p.x) * (r.y - q.y);
		return slope1 < slope2; 
	}

	// Prints convex hull of a set of n points.
	public static void convexHull(Point points[], int n, FramesViewer fv) {
		// There must be at least 3 points
		// TODO Report Invalid Input
		if (n < 3)
			return;
		
		// Draw Points


		// Initialize Result
		List<Point> hull = new ArrayList<Point>();

		// Find the leftmost point
		// TODO Report Starting Point
		int leftmost = 0;
		for (int i = 1; i < n; i++)
			if (points[i].x < points[leftmost].x)
				leftmost = i;

		int p = leftmost, q;
		do {
			// Add current point to result
			hull.add(points[p]);

			q = (p + 1) % n;

			for (int i = 0; i < n; i++) {
				if (isLeftTurn(points[p], points[i], points[q])) {
					// TODO Report New Convex Vertex and Edge
					q = i;
				}else {
					// TODO Unmark determined vertex and edge
					
				}
			}

			// Now q is the most counterclockwise with
			// respect to p. Set p as q for next iteration,
			// so that q is added to result 'hull'
			p = q;

		} while (p != leftmost); // While we don't come to first point

		// Print Result
		// TODO Report Finnal Result
		for (Point temp : hull)
			System.out.println("(" + temp.x + ", " + temp.y + ")");
	}

	/* Driver program to test above function */
	public static void main(String[] args) {

		Point points[] = new Point[7];
		points[0] = new Point(0, 3);
		points[1] = new Point(2, 3);
		points[2] = new Point(1, 1);
		points[3] = new Point(2, 1);
		points[4] = new Point(3, 0);
		points[5] = new Point(0, 0);
		points[6] = new Point(3, 3);

		int n = points.length;
		convexHull(points, n, new FramesViewer());

	}

	// This code is contributed by Arnav Kr. Mandal.

}

