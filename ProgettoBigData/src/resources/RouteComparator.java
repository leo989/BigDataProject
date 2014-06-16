package resources;

import java.util.Comparator;

public class RouteComparator implements Comparator<Route> {

	@Override
	public int compare(Route p1, Route p2) {
		return Double.compare(p1.getLength(), p2.getLength());
	}

}
