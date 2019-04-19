package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;

import Model.Point;
import javafx.util.Pair;

public class Single_Frame extends JPanel {
	
	List<Point> points;
	List<Point> hull;
	List<Pair<Point,Point>> hull_edges;
	List<Pair<Point,Point>> intermediate;
	Map<Integer, Pair<Point,Point>> map; 
	int level_active_points;
	boolean isFinished;
	List<Point> currents;
	
	int radius = 3;
	
	public Single_Frame() {
		points = new ArrayList<Point>();
		hull = new ArrayList<Point>();
		hull_edges = new ArrayList<Pair<Point,Point>>();
		intermediate = new ArrayList<Pair<Point,Point>> ();
		map = new HashMap<Integer, Pair<Point, Point>>();
		isFinished = false;
		currents = new ArrayList<Point>();
		level_active_points = 1;
		this.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    Graphics2D g2d = (Graphics2D) g;
	    drawHull(g2d);
	    drawAllPoints(g2d);
	    drawIntermediate(g2d);
	    drawCurrentPoints(g2d);
	}
	
	private int getY(int y) {
		return this.getHeight() - y;
	}
	
	private void drawCurrentPoints(Graphics2D g2d) {
		g2d.setColor(Color.orange);
		for(int i = 0; i < Math.min(level_active_points, currents.size()); i ++) {
			Ellipse2D.Double circle = 
					new Ellipse2D.Double(currents.get(i).x - (radius + 1),currents.get(i).y - (radius + 1), (radius + 1) * 2, (radius + 1) * 2);
			g2d.fill(circle);
		}
		g2d.setColor(Color.red);
		for(int i = level_active_points; i < currents.size(); i ++) {
			Ellipse2D.Double circle = 
					new Ellipse2D.Double(currents.get(i).x - (radius + 1),currents.get(i).y - (radius + 1), (radius + 1) * 2, (radius + 1) * 2);
			g2d.fill(circle);
		}
	}
	
	public void setCurrent(Point p) {
		currents.add(p);
	}
	
	private void drawAllPoints(Graphics2D g2d) {
		g2d.setColor(Color.black);
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
	public List<Point> getPoints(){
		return points;
	}
	private void drawHull(Graphics2D g2d) {
		g2d.setColor(Color.green);
		g2d.setStroke(new BasicStroke(2));
		if(hull.size() != 0) {
			for(int i = 1; i < hull.size(); i ++) 
				g2d.drawLine(hull.get(i-1).x, hull.get(i-1).y, hull.get(i).x, hull.get(i).y);
			if(isFinished)
				g2d.drawLine(hull.get(hull.size()-1).x, hull.get(hull.size()-1).y, hull.get(0).x, hull.get(0).y);
		}else if(hull_edges.size() != 0) {
			for(int i = 0; i < hull_edges.size(); i ++) {
				Point p1 = hull_edges.get(i).getKey();
				Point p2 = hull_edges.get(i).getValue();
				g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
			}
		}
	}
	public void setFinish(boolean b) {
		isFinished = b;
	}
	public void addHullPoint(Point p) {
		hull.add(p);
	}
	public void addHullPoints(List<Point> hulls) {
		hull.addAll(hulls);
	}
	
	public void addHullPointsByEdge(Point p1, Point p2) {
		hull_edges.add(new Pair<Point,Point>(p1,p2));
	}
	
	public List<Point> getHull(){
		return hull;
	}
	
	public List<Pair<Point,Point>> getHullEdges(){
		return hull_edges;
	}
	
	private void drawIntermediate(Graphics2D g2d) {
		g2d.setColor(Color.red);
		for(Pair p : intermediate) {
			Point p1 = (Point) p.getKey();
			Point p2 = (Point) p.getValue();
			g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
	}
	public int addIntermediate(Point p1, Point p2) {
		Pair<Point, Point> pair = new Pair<Point, Point>(p1, p2);
		intermediate.add(pair);
		int i = (int)(Math.random() * Integer.MAX_VALUE);
		while(map.containsKey(i)) i = (int)(Math.random() * Integer.MAX_VALUE);
		map.put(i, pair);
		return i;
	}
	public void addIntermediates(List<Pair<Point,Point>> l) {
		intermediate.addAll(l);
	}
	public void delteIntermediate(int pos) {
		if(intermediate.isEmpty()) return;
		Pair<Point, Point> target = map.get(pos);
		Point p1 = target.getKey();
		Point p2 = target.getValue();
		map.remove(pos);
		for(int i = 0; i < intermediate.size(); i++) {
			Pair<Point, Point> pair = intermediate.get(i);
			Point ptr1 = pair.getKey();
			Point ptr2 = pair.getValue();
			if(ptr1.equals(p1) && ptr2.equals(p2)) {
				intermediate.remove(i);
				return;
			}
		}
		System.out.println("Didn't find intermediate.");
	}
	public List<Pair<Point,Point>> getIntermediate(){
		return intermediate;
	}
	public Map<Integer, Pair<Point, Point>> getMap(){
		return map;
	}
	public List<Point> getCurrents(){
		return currents;
	}
	public void clearCurrentPoints() {
		currents = new ArrayList<Point>();
	}
	public void setLevelActivePoints(int c) {
		level_active_points = c;
	}
	public int getLevelActivePoints() {
		return level_active_points;
	}
	public Single_Frame(Single_Frame other) {
		points = new ArrayList<Point>(other.getPoints());
		hull = new ArrayList<Point>(other.getHull());
		hull_edges = new ArrayList<Pair<Point,Point>>(other.getHullEdges());
		intermediate = new ArrayList<Pair<Point,Point>>(other.getIntermediate());
		map = new HashMap<Integer, Pair<Point, Point>>(other.getMap());
		currents = new ArrayList<Point>();
		currents = new ArrayList<Point>(other.getCurrents());
		level_active_points = other.getLevelActivePoints();
	}
}
