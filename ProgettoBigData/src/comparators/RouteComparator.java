package comparators;

import java.util.Comparator;

import resources.Route;

public class RouteComparator implements Comparator<Route> {

	@Override
	public int compare(Route p1, Route p2) {
		return Double.compare(p1.getLength(), p2.getLength());
	}

}
