package utils;

import java.util.ArrayList;
import java.util.List;

import resources.Point;
import resources.Product;

public class EligibleRoutesCombinator {
	
	private int maxJumps;
	private ArrayList<ArrayList<Point>> eligibleRoutes;
	private String productName;
	private Integer maxQuantity;
	
	public EligibleRoutesCombinator(String productName, Integer quantity, int maxJumps) {
		this.productName = productName;
		this.maxQuantity = quantity;
		eligibleRoutes = new ArrayList<ArrayList<Point>>();
		this.maxJumps = maxJumps;
	}
	
	public ArrayList<ArrayList<Point>> getEligibles(Point start, List<Point> points) {
		ArrayList<Point> combination = new ArrayList<Point>();
		combination.add(start);
		this.recursiveEligibles(points, combination, 0, 0);
		return eligibleRoutes;
	}

	private void recursiveEligibles(List<Point> points, ArrayList<Point> combination, int sum, int jumps) {
		Point point;
		Product prod;
		if (!points.isEmpty() && jumps < this.maxJumps) {
			for (int i = 0; i < points.size(); i++) {
				point = points.get(i);
				prod = point.getProduct(productName);
				if (combination.size() == 1 || prod.getQuantity() < maxQuantity) {
					ArrayList<Point> tmp = new ArrayList<Point>(combination);
					tmp.add(point);
					if (sum + prod.getQuantity() >= maxQuantity) {
						eligibleRoutes.add(tmp);
					} else {
						recursiveEligibles(points.subList(i + 1, points.size()),
										   tmp,
										   sum + prod.getQuantity(),
										   jumps + 1);
					}
				}
			}
		}
	}
	
}
