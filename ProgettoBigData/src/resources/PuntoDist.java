package resources;

public class PuntoDist extends Punto implements Comparable<PuntoDist>{
	private double dist;
	public PuntoDist(double latitudine, double longitudine) {
		super(latitudine, longitudine);
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

	
}
