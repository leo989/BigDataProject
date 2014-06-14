package resources;

import java.util.Comparator;

public class RouteComparator implements Comparator<Percorso> {

	@Override
	public int compare(Percorso p1, Percorso p2) {
		return Double.compare(p1.getLunghezza(), p2.getLunghezza());
	}

}
