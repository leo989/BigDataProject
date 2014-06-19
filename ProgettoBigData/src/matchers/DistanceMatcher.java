package matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import resources.DistanceCalculator;
import resources.Point;

public class DistanceMatcher extends BaseMatcher<Object>{

	private Point start;
	private double maxDistance;
	private DistanceCalculator dc;

	public DistanceMatcher(Point startingPoint, double maxDistance) {
		this.start = startingPoint;
		this.maxDistance = maxDistance;
		this.dc = new DistanceCalculator();
	}
	
	@Override
	public boolean matches(Object arg0) {
		Point point = (Point) arg0;
		double distance = this.dc.get2PointsDistanceInKm(start, point);
		return distance <= maxDistance;
	}

	@Override
	public void describeTo(Description arg0) {		
	}
	
	

}
