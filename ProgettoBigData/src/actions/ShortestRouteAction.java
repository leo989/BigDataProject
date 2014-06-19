package actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparators.AerialDistanceComparator;
import comparators.RouteComparator;
import matchers.DistanceMatcher;
import matchers.QuantityMatcher;
import ch.lambdaj.Lambda;
import resources.Dataset;
import resources.EligibleRoutesCombinator;
import resources.Route;
import resources.Point;
import resources.PointWithDistance;
import servlets.SortPoints;

public class ShortestRouteAction {

	private String productName;
	private Integer quantity;
	private Point startingPoint;
	private boolean enableAPIs;

	public ShortestRouteAction(String product, Integer quantity, Point startingPoint, boolean enableAPIs) {
		this.quantity = quantity;
		this.productName = product;
		this.startingPoint = startingPoint;
		this.enableAPIs = enableAPIs;
	}

	public List<Point> getRoute() {
		List<Point> totalPoints = SortPoints.sortByDistanceAndReturnPoint(startingPoint, Dataset.getPointByProduct(productName), this.enableAPIs);
		List<Route> totalRoute = new ArrayList<Route>();
		while (totalPoints.size()>0) {
			totalRoute.add(this.getValidRoute(startingPoint, totalPoints, this.enableAPIs));
			totalPoints.remove(0);
		}
		Collections.sort(totalRoute, new RouteComparator());
		return this.route2points(totalRoute.get(0));
	}
	
	public ArrayList<ArrayList<Point>> getPotentialRoutes() {
		List<Point> points = Dataset.getPointByProduct(productName);
		List<Point> exhaustivePoints = Lambda.filter(new QuantityMatcher(productName, quantity), points);	//punti che soddisfano da soli la richiesta
		if (!points.isEmpty()) { 
			Point nearestExhaustivePoint = Collections.min(exhaustivePoints, new AerialDistanceComparator(startingPoint));	//punto più vicino che soddisfa da solo la richiesta
			List<Point> validPoints = Lambda.filter(new DistanceMatcher(startingPoint, nearestExhaustivePoint), points);	//punti dentro il raggio del punto più vicino che soddisfa da solo la richiesta
			EligibleRoutesCombinator erc = new EligibleRoutesCombinator(productName, quantity);
			return erc.getEligibles(startingPoint, validPoints);
		} else { //there's no point who fulfills the quantity request
			//to do
			return null;
		}		
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
		PointWithDistance point = this.getNearest(from, toVisit,enableAPIs);
		if(point != null){
			route.add(point);
			route.aggQuantity(point.getProduct(productName).getQuantity());
			while (route.getQuantity() < this.quantity) {
				List<Point> toVisitBis = this.removePointById(point.getId(),toVisit);
				point = this.getNearest(point, toVisitBis,enableAPIs);
				if(point!=null){
					route.add(point);
					route.aggQuantity(point.getProduct(productName).getQuantity());
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

	private PointWithDistance getNearest(Point from, List<Point> points, boolean enableAPIs2) {
		List<PointWithDistance> points2 = SortPoints.sortByDistance(from, points,enableAPIs);
		if (points.isEmpty())
			return null;
		return points2.get(0);
	}

}
