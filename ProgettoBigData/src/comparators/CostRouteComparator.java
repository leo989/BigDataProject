package comparators;

import java.util.Comparator;

import resources.Route;

public class CostRouteComparator implements Comparator<Route> {
	@Override
	public int compare(Route p1, Route p2) {
		int result = Double.compare(p1.getTotalCost(), p2.getTotalCost());
		if(result == 0)
			return Double.compare(p1.getLength(), p2.getLength());
		else
			return result;
	}
}
