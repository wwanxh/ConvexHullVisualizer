package Model;

public class Point{
	public int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object p) {
		Point pt = (Point) p;
		if(this.x == pt.x && this.y == pt.y)
			return true;
		else 
			return false;
	}

}
