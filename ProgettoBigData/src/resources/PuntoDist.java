package resources;

public class PuntoDist extends Punto implements Comparable<PuntoDist>{
	private double dist;
	private boolean visited;
	public PuntoDist(double latitudine, double longitudine, long id) {
		super(latitudine, longitudine, id);
		this.visited  = false;
	}
	public double getDist() {
		return dist;
	}
	public void setDist(double dist) {
		this.dist = dist;
	}
	
	public int compareTo(PuntoDist p){
		return (int) (this.dist - p.getDist());
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	
}
