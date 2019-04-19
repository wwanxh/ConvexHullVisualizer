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
	public Jarvis(Point[] points, FramesViewer fv) {
		// There must be at least 3 points
		// TODO Report Invalid Input
		if (points.length < 3)
			return;
		
		// Draw Points
		// -----------
		fv.initFrame(points, 2);
		// -----------

		// Initialize Result
		List<Point> hull = new ArrayList<Point>();

		// Find the leftmost point
		int leftmost = 0;
		for (int i = 1; i < points.length; i++)
			if (points[i].x < points[leftmost].x)
				leftmost = i;

		int p = leftmost, q;
		do {
			// Add current point to result
			hull.add(points[p]);
			
			// TODO Draw Hull Vertices
			// -----------
			fv.addHull(points[p]);
			// -----------

			q = (p + 1) % points.length;

			for (int i = 0; i < points.length; i++) {
				// TODO Report current points
				// -----------
				fv.setCurrentPoints(points[p], points[q], points[i]);
				// -----------
				
				// TODO Report Intermediate Edges
				int i1 = fv.addIntermediate(points[p], points[i]);
				int i2 = fv.addIntermediate(points[i], points[q]);
				
				if (isLeftTurn(points[p], points[i], points[q])) {
					q = i;
				}
				
				// TODO Delete Intermediate Edges
				// -----------
				fv.deleteIntermediate(i2);
				fv.deleteIntermediate(i1);
				fv.clearCurrentPoints();
				// -----------
				
			}

			// Now q is the most counterclockwise with
			// respect to p. Set p as q for next iteration,
			// so that q is added to result 'hull'
			p = q;

		} while (p != leftmost); // While we don't come to first point

		// Print Result
		
		// TODO Report Finnal Result
		// -----------
		fv.finishPresent();
		// -----------
		
		for (Point temp : hull)
			System.out.println("(" + temp.x + ", " + temp.y + ")");
	}

	/* Driver program to test above function */
	public static void main(String[] args) {

//		Point points[] = {new Point(0, 300), new Point(200, 400), 
//				new Point(100, 100), new Point(200, 100), new Point(600, 0),
//				new Point(100, 50), new Point(450, 300)};
//		new Jarvis(points, new FramesViewer());
		
		Point[] points = { new Point(50, 300), new Point(100, 100), new Point(200, 200), new Point(400, 400),
				new Point(50, 50), new Point(100, 200), new Point(300, 100), new Point(300, 300) };
		new Jarvis(points, new FramesViewer());

	}
}

