package comparators;

import java.util.Comparator;

import resources.DistanceCalculator;
import resources.Point;

public class AerialDistanceComparator implements Comparator<Point> {

	private Point start;
	private DistanceCalculator dc;

	public AerialDistanceComparator(Point startingPoint) {
		this.start = startingPoint;
		this.dc = new DistanceCalculator();
	}
	
	@Override
	public int compare(Point p1, Point p2) {
		double d1 = this.dc.get2PointsDistanceInKm(start, p1);
		double d2 = this.dc.get2PointsDistanceInKm(start, p2);
		return Double.compare(d1, d2);
	}
}