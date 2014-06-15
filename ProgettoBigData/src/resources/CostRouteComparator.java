package resources;

import java.util.Comparator;

public class CostRouteComparator implements Comparator<Percorso> {
	@Override
	public int compare(Percorso p1, Percorso p2) {
		return Double.compare(p1.getTotalCost(), p2.getTotalCost());
	}
}
