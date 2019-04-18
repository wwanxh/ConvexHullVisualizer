package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import Model.Point;
import javafx.util.Pair;

public class Single_Frame extends JPanel {
	
	List<Point> points;
	List<Point> hull;
	List<Pair<Point,Point>> intermediate;
	boolean isFinished;
	
	public Single_Frame() {
		points = new ArrayList<Point>();
		hull = new ArrayList<Point>();
		intermediate = new ArrayList<Pair<Point,Point>> ();
		isFinished = false;
		this.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    Graphics2D g2d = (Graphics2D) g;
	    drawIntermediate(g2d);
	    drawHull(g2d);
	    drawAllPoints(g2d);
	}
	
	private int getY(int y) {
		return this.getHeight() - y;
	}
	
	private void drawAllPoints(Graphics2D g2d) {
		g2d.setColor(Color.black);
		int radius = 3;
		for(Point p : points){
			Ellipse2D.Double circle = 
					new Ellipse2D.Double(p.x - radius,p.y - radius, radius * 2, radius * 2);
			g2d.fill(circle);
		}
	}
	public void addPoint(Point p) {
		points.add(p);
	}
	public void addPoints(Point[] p_arr) {
		for(Point p : p_arr) points.add(p);
	}
	private void drawHull(Graphics2D g2d) {
		g2d.setColor(Color.green);
		g2d.setStroke(new BasicStroke(2));
		for(int i = 1; i < hull.size(); i ++) 
			g2d.drawLine(hull.get(i-1).x, hull.get(i-1).y, hull.get(i).x, hull.get(i).y);
		if(isFinished)
			g2d.drawLine(hull.get(hull.size()-1).x, hull.get(hull.size()-1).y, hull.get(0).x, hull.get(0).y);
	}
	public void setFinish(boolean b) {
		isFinished = b;
	}
	public void addHullPoint(Point p) {
		hull.add(p);
	}
	private void drawIntermediate(Graphics2D g2d) {
		g2d.setColor(Color.red);
		for(Pair p : intermediate) {
			Point p1 = (Point) p.getKey();
			Point p2 = (Point) p.getValue();
			g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
	}
	public void addIntermediate(Point p1, Point p2) {
		intermediate.add(new Pair<Point, Point>(p1, p2));
	}
}
