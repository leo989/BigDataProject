package actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import matchers.DistanceMatcher;
import ch.lambdaj.Lambda;
import comparators.RouteComparator;
import resources.Dataset;
import resources.EligibleRoutesCombinator;
import resources.Route;
import resources.Point;
import resources.PointWithDistance;
import servlets.SortPoints;

public class BestTotalCostAction {
	private String product;
	private double quantity;
	private Point user;
	private double kmWeight;
	private boolean enableAPIs;
	private double maxDistance;
	private int maxNumberOfPoint;

	public BestTotalCostAction(String product, int quantity, Point userPoint, double kmPerLiter, double euroPerLiter, boolean enableAPIs, int maxDistance, int maxNumberOfPoint) {
		this.product = product;
		this.quantity = quantity;
		this.user = userPoint;
		this.kmWeight = euroPerLiter/kmPerLiter;
		this.enableAPIs = enableAPIs;
		this.maxDistance = maxDistance;
		this.maxNumberOfPoint = maxNumberOfPoint;
	}

	public List<Point> getRoute() {
		List<Route> totalRoute = new ArrayList<Route>();
		List<ArrayList<Point>> partsOfPoints =  this.getPotentialRoutes();
		if(partsOfPoints != null) {
			for(List<Point> list: partsOfPoints){
				Route route = this.getValidRoute(user, list, enableAPIs);
				if(route != null){
					route.aggTotalCost(product, quantity, kmWeight);
					totalRoute.add(route);
				}
			}
			if(!totalRoute.isEmpty()){
				Route r = Collections.min(totalRoute, new RouteComparator());
				return this.route2points(r);
			}
		}
		return null;
	}
	
	public ArrayList<ArrayList<Point>> getPotentialRoutes() {
		List<Point> points = Dataset.getPointByProduct(product);
		List<Point> validPoints = Lambda.filter(new DistanceMatcher(user, maxDistance), points);	//punti entro un'area di raggio teorico massimo
		EligibleRoutesCombinator erc = new EligibleRoutesCombinator(product, (int) quantity, this.maxNumberOfPoint);
		return erc.getEligibles(user, validPoints);
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

	private Route getValidRoute(Point from, List<Point> toVisit, boolean enableAPIs2) {
		Route route = new Route();
		toVisit = this.removePointById(user.getId(), toVisit);
		PointWithDistance point = this.getNearest(from, toVisit,enableAPIs);
		if(point != null){
			route.add(point);
			route.aggQuantity(point.getProduct(product).getQuantity());
			while (route.getQuantity() < this.quantity && route.getPoints().size()<this.maxNumberOfPoint) {
				List<Point> toVisitBis = this.removePointById(point.getId(),toVisit);
				point = this.getNearest(point, toVisitBis,enableAPIs);
				if(point!=null){
					route.add(point);
					route.aggQuantity(point.getProduct(product).getQuantity());
					toVisit = toVisitBis;
				}else{
					return null;
				}
			}
		return route;
		}else{
			return null;
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

	private PointWithDistance getNearest(Point from, List<Point> points, boolean enableAPIs2) {
		List<PointWithDistance> points2 = SortPoints.sortByDistance(from, points,enableAPIs);
		if (points.isEmpty())
			return null;
		return points2.get(0);
	}

}
