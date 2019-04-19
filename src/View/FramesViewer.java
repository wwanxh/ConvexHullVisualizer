package View;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import Model.Point;

public class FramesViewer extends JFrame {
	
	ArrayList<Single_Frame> frames = new ArrayList<Single_Frame>();
	int current;

	public FramesViewer() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.LIGHT_GRAY);
		this.setSize(600,600);
		this.setVisible(true);
	}
	public void presentFrame(Single_Frame sf) {
		this.getContentPane().removeAll();
		this.getContentPane().add(sf);
		this.validate();
	}
	private void presentFrame(int i) {
		this.getContentPane().removeAll();
		this.getContentPane().add(frames.get(i));
		this.validate();
		
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initFrame(Point[] points, int level_active_points) {
		current = frames.size();
		frames.add(new Single_Frame());
		frames.get(current).addPoints(points);
		frames.get(current).setLevelActivePoints(level_active_points);
		presentFrame(current);
	}
	
	public void addHull(Point p) {
		current = frames.size();
		frames.add(new Single_Frame(frames.get(current - 1)));
		frames.get(current).addHullPoint(p);
		presentFrame(current);
	}
	public void addHullByEdge(Point p1, Point p2) {
		current = frames.size();
		frames.add(new Single_Frame(frames.get(current - 1)));
		frames.get(current).addHullPointsByEdge(p1,p2);
		presentFrame(current);
	}
	
	public int addIntermediate(Point p1, Point p2) {
		current = frames.size();
		frames.add(new Single_Frame(frames.get(current - 1)));
		int i = frames.get(current).addIntermediate(p1, p2);
		presentFrame(current);
		return i;
	}
	
	public void deleteIntermediate(int i) {
		current = frames.size();
		frames.add(new Single_Frame(frames.get(current - 1)));
		frames.get(current).delteIntermediate(i);
		presentFrame(current);
	}
	
	public void setCurrentPoints(Point ... pt) {
		current = frames.size();
		frames.add(new Single_Frame(frames.get(current - 1)));
		for(Point p : pt)
			frames.get(current).setCurrent(p);
		presentFrame(current);
	}
	public void clearCurrentPoints() {
		current = frames.size();
		frames.add(new Single_Frame(frames.get(current - 1)));
		frames.get(current).clearCurrentPoints();
	}
	public void finishPresent() {
		current = frames.size();
		frames.add(new Single_Frame(frames.get(current - 1)));
		frames.get(current).setFinish(true);
		presentFrame(current);
	}
	
	public static void main(String[] args) {
		FramesViewer fv = new FramesViewer();
		
		Single_Frame sf = new Single_Frame();
		sf.setSize(new Dimension(500,500));
		sf.addPoint(new Point(10,10));
		sf.addPoint(new Point(250,100));
		sf.addPoint(new Point(300,400));
		//sf.addHullPoint(new Point(10,10));
		//sf.addHullPoint(new Point(250,100));
		//sf.addHullPoint(new Point(300,400));
		//sf.setFinish(true);
		//sf.addPoint(new Point(200, 200));
		//sf.addIntermediate(new Point(10,10), new Point(200, 200));
		fv.presentFrame(sf);
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Next Step..");
		
		Single_Frame sf1 = new Single_Frame();
		sf1.setSize(new Dimension(500,500));
		sf1.addPoint(new Point(10,10));
		sf1.addPoint(new Point(250,100));
		sf1.addPoint(new Point(300,400));
		//sf.addHullPoint(new Point(10,10));
		//sf.addHullPoint(new Point(250,100));
		//sf.addHullPoint(new Point(300,400));
		//sf.setFinish(true);
		sf1.addPoint(new Point(200, 200));
		sf1.addIntermediate(new Point(10,10), new Point(200, 200));
		fv.presentFrame(sf1);
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Next Step..");
		
		Single_Frame sf2 = new Single_Frame();
		sf2.setSize(new Dimension(500,500));
		sf2.addPoint(new Point(10,10));
		sf2.addPoint(new Point(250,100));
		sf2.addPoint(new Point(300,400));
		sf2.addHullPoint(new Point(10,10));
		sf2.addHullPoint(new Point(250,100));
		sf2.addHullPoint(new Point(300,400));
		sf2.setFinish(true);
		sf2.addPoint(new Point(200, 200));
		sf2.addIntermediate(new Point(10,10), new Point(200, 200));
		fv.presentFrame(sf2);
		
		
	}
		
}
