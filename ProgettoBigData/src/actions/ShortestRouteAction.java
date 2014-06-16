package actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import resources.Dataset;
import resources.Route;
import resources.Point;
import resources.PointWithDistance;
import resources.RouteComparator;
import servlets.SortPoints;

public class ShortestRouteAction {

	private String product;
	private double quantity;
	private Point user;

	public ShortestRouteAction(String product, double quantity, Point user) {
		this.quantity = quantity;
		this.product = product;
		this.user = user;
	}

	public List<Point> getRoute() {
		List<Point> totalPoints = SortPoints.sortByDistanceAndReturnPoint(user, Dataset.getPointByProduct(product));
		List<Route> totalRoute = new ArrayList<Route>();
		while(totalPoints.size()>0){
			totalRoute.add(this.getValidRoute(user, totalPoints));
			totalPoints.remove(0);
		}
		Collections.sort(totalRoute, new RouteComparator());
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
