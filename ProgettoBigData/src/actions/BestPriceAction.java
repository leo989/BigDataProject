package actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import resources.Dataset;
import resources.PriceComparator;
import resources.Punto;

public class BestPriceAction {
	
	private String product;
	private int quantity;

	public BestPriceAction(String product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public List<Punto> getRoute() {
		List<Punto> points = Dataset.getPuntiByProdotto(this.product);
		Collections.sort(points, new PriceComparator(this.product));
		int c = 0;
		List<Punto> pointsToReturn = new ArrayList<Punto>();
		for (Punto point : points) {
			c += point.searchProdotto(product).getQuantità();
			pointsToReturn.add(point);
			if (c >= quantity)
				return pointsToReturn;
		}
		return pointsToReturn;
	}
	
}
