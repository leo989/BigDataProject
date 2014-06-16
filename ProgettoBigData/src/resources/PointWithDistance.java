package resources;

public class PointWithDistance extends Point implements Comparable<PointWithDistance>{
	private double dist;
	public PointWithDistance(double latitude, double longitude, long id) {
		super(latitude, longitude, id);
	}
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}
	
	public int compareTo(PointWithDistance p){
		return (int) (this.dist - p.getDist());
	}
}
