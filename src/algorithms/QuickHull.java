package algorithms;

import java.util.*;
import Model.Point;
import View.FramesViewer;
import javafx.util.Pair;

public class QuickHull {

	Point[] points;
	List<Point> hull;
	FramesViewer fv;

	public QuickHull(Point[] points, FramesViewer fv) {
		this.points = points;

		// TODO Draw Points
		// -----------
		fv.initFrame(points, 0);
		// -----------

		hull = new ArrayList<Point>();
		this.fv = fv;
		printHull();
	}

	public int findSide(Point p1, Point p2, Point p) {
		int val = (p.y - p1.y) * (p2.x - p1.x) - (p2.y - p1.y) * (p.x - p1.x);
		return val > 0 ? 1 : val < 0 ? -1 : 0;
	}

	public int lineDist(Point p1, Point p2, Point p) {
		return Math.abs((p.y - p1.y) * (p2.x - p1.x) - (p2.y - p1.y) * (p.x - p1.x));
	}

	public void quickhull(Point p1, Point p2, int side) {
		int ind = -1;
		int max_dist = 0;

		for (int i = 0; i < points.length; i++) {
			// TODO Draw Current Point
			// -----------
			fv.setCurrentPoints(points[i]);
			// -----------
			int temp = lineDist(p1, p2, points[i]);
			if (findSide(p1, p2, points[i]) == side && temp > max_dist) {
				ind = i;
				max_dist = temp;
			}
			// TODO Delete Current Point
			// -----------
			fv.clearCurrentPoints();
			// -----------
		}

		if (ind == -1) {
			hull.add(p1);
			hull.add(p2);

			// TODO Report Hull Points
			// -----------
			fv.addHullByEdge(p1, p2);
			// -----------

			return;
		}
		// TODO Draw Joining Line
		// -----------
		int t = fv.addIntermediate(points[ind], p1);
		// -----------
		quickhull(points[ind], p1, -findSide(points[ind], p1, p2));
		// TODO delete Joining Line
		// -----------
		fv.deleteIntermediate(t);
		// -----------

		// TODO Draw Joining Line
		// -----------
		t = fv.addIntermediate(points[ind], p2);
		// -----------
		quickhull(points[ind], p2, -findSide(points[ind], p2, p1));
		// TODO delete Joining Line
		// -----------
		fv.deleteIntermediate(t);
		// -----------

	}

	void printHull() {
		if (points.length < 3)
			return;
		int min_x = 0, max_x = 0;
		for (int i = 1; i < points.length; i++) {
			if (points[i].x < points[min_x].x)
				min_x = i;
			if (points[i].y > points[max_x].x)
				max_x = i;
		}
		// TODO Draw Joining Line
		// -----------
		int t = fv.addIntermediate(points[min_x], points[max_x]);
		// -----------
		quickhull(points[min_x], points[max_x], 1);
		// TODO delete Joining Line
		// -----------
		fv.deleteIntermediate(t);
		// -----------
		
		// TODO Draw Joining Line
		// -----------
		t = fv.addIntermediate(points[min_x], points[max_x]);
		// -----------
		quickhull(points[min_x], points[max_x], -1);
		// TODO delete Joining Line
		// -----------
		fv.deleteIntermediate(t);
		// -----------
		
		System.out.println("Convex Hull: ");
		for (Point p : hull) {
			System.out.println("(" + p.x + "," + p.y + ")");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Point points[] = {new Point(0, 300), new Point(200, 400), 
				new Point(100, 100), new Point(200, 100), new Point(600, 0),
				new Point(100, 50), new Point(450, 300)};
		new QuickHull(points, new FramesViewer());
		
		
//		Point[] points = { new Point(50, 300), new Point(100, 100), new Point(200, 200), new Point(400, 400),
//				new Point(50, 50), new Point(100, 200), new Point(300, 100), new Point(300, 300) };
//		new QuickHull(points, new FramesViewer());
		
	}

}
