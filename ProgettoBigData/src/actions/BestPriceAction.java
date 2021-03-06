package actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparators.PriceComparator;
import resources.Dataset;
import resources.Point;

public class BestPriceAction {
	
	private Point startingPoint;
	private String product;
	private int quantity;	

	public BestPriceAction(Point start, String product, int quantity) {
		this.startingPoint = start;
		this.product = product;
		this.quantity = quantity;
	}
	
	public List<Point> getRoute() {
		List<Point> points = Dataset.getPointByProduct(this.product);
		Collections.sort(points, new PriceComparator(this.product));
		int c = 0;
		List<Point> pointsToReturn = new ArrayList<Point>();
		pointsToReturn.add(startingPoint);
		for (Point point : points) {
			c += point.getProduct(product).getQuantity();
			pointsToReturn.add(point);
			if (c >= quantity)
				return pointsToReturn;
		}
		return null;
	}
	
}
