package actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import resources.CostRouteComparator;
import resources.Dataset;
import resources.DistanceMatrix;
import resources.Route;
import resources.Point;
import resources.PointWithDistance;
import servlets.SortPoints;

public class BestTotalCostAction {
	private String product;
	private double quantity;
	private Point user;
	private double kmWeight;

	public BestTotalCostAction(String product, int quantity, Point userPoint, double kmPerLiter, double euroPerLiter) {
		this.product = product;
		this.quantity = quantity;
		this.user = userPoint;
		this.kmWeight = euroPerLiter/kmPerLiter;
	}

	public List<Point> getRoute() {
		List<Point> totalPoints = SortPoints.sortByDistanceAndReturnPoint(user, Dataset.getPointByProduct(product));
		List<Route> totalRoute = new ArrayList<Route>();
		while(totalPoints.size()>0){
			Route route = this.getValidRoute(user, totalPoints);
			PointWithDistance userPoint = new PointWithDistance(user.getLatitude(),user.getLongitude(),user.getId());
			userPoint.setDist(DistanceMatrix.getDistance(route.getPoints().get(route.getPoints().size()-1).getId(), user.getId()));
			route.add(userPoint);
			route.aggTotalCost(product, quantity, kmWeight);
			totalRoute.add(route);
			totalPoints.remove(0);
		}
		Collections.sort(totalRoute, new CostRouteComparator());
		return this.route2points(totalRoute.get(0));
	}

	private List<Point> route2points(Route percorso) {
		List<Point> points = new ArrayList<Point>();
		for(PointWithDistance pt: percorso.getPoints()){
			Point p = new Point(pt.getLatitude(), pt.getLongitude(), pt.getId());
			p.setProducts(pt.getProducts());
			points.add(p);
		}
		return points;
	}

	private Route getValidRoute(Point from, List<Point> toVisit) {
		Route route = new Route();
		PointWithDistance point = this.getNearest(from, toVisit);
		if(point != null){
			route.add(point);
			route.aggQuantity(point.searchProduct(product).getQuantity());
			while (route.getQuantity() < this.quantity) {
				List<Point> toVisitBis = this.removePointById(point.getId(),toVisit);
				point = this.getNearest(point, toVisitBis);
				if(point!=null){
					route.add(point);
					route.aggQuantity(point.searchProduct(product).getQuantity());
					toVisit = toVisitBis;
				}else{
					return route;
				}
			}
		return route;
		}else{
			return route;
		}
	}

	private List<Point> removePointById(long id, List<Point> punti) {
		List<Point> points = new ArrayList<Point>();
		for (Point p : punti) {
			if (p.getId() != id)
				points.add(p);
		}
		return points;
	}

	private PointWithDistance getNearest(Point from, List<Point> points) {
		List<PointWithDistance> points2 = SortPoints.sortByDistance(from, points);
		if (points.isEmpty())
			return null;
		return points2.get(0);
	}

}
