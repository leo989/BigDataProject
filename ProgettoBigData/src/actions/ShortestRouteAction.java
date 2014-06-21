package actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import comparators.AerialDistanceComparator;
import comparators.RouteComparator;
import matchers.DistanceMatcher;
import matchers.QuantityMatcher;
import ch.lambdaj.Lambda;
import resources.Dataset;
import resources.DistanceCalculator;
import resources.EligibleRoutesCombinator;
import resources.Route;
import resources.Point;
import resources.PointWithDistance;
import servlets.SortPoints;

public class ShortestRouteAction {

	private static final double MAX_DISTANCE = 200;
	  private final static int NCPU = Runtime.getRuntime().availableProcessors();

	
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
		List<Route> totalRoute = new ArrayList<Route>();
		List<ArrayList<Point>> partsOfPoints =  this.getPotentialRoutes();
		if(partsOfPoints != null) {
			for(List<Point> list: partsOfPoints){
				Route route = this.getValidRoute(startingPoint, list, enableAPIs);
				if(route != null)
					totalRoute.add(route);
			}
			if(!totalRoute.isEmpty()){
				Route r = Collections.min(totalRoute, new RouteComparator());
				return this.route2points(r);
			}
		}
		return null;
	}
	
	public List<Point> getRouteConcurrence() {
		System.out.println("CONCORRENTE");
		List<ArrayList<Point>> partsOfPoints =  this.getPotentialRoutes();
		if(partsOfPoints != null) {
			ExecutorService pool = Executors.newFixedThreadPool(NCPU);
			List<Future<Route>> pending = new LinkedList<Future<Route>>();
			for (List<Point> points: partsOfPoints) {
				Worker worker = new Worker(points);
				pending.add(pool.submit(worker));
			}
			RouteComparator rc = new RouteComparator();
			Route shortest = null;
			try {
				shortest = pending.get(0).get();
				for (int i = 1; i < pending.size(); i++) {
					Route r = pending.get(i).get();
					if (rc.compare(shortest, r) > 0)
						shortest = r;
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			return this.route2points(shortest);
		}
		return null;
	}
	
	private class Worker implements Callable<Route> {
		
		private List<Point> points;

		public Worker(List<Point> points) {
			this.points = points;
		}

		@Override
		public Route call() throws Exception {
			return getValidRoute(startingPoint, points, enableAPIs);
		}		
	}

	public ArrayList<ArrayList<Point>> getPotentialRoutes() {
		List<Point> points = Dataset.getPointByProduct(productName);
		List<Point> exhaustivePoints = Lambda.filter(new QuantityMatcher(productName, quantity), points);	//punti che soddisfano da soli la richiesta
		List<Point> validPoints;
		if (!exhaustivePoints.isEmpty()) { 
			Point nearestExhaustivePoint = Collections.min(exhaustivePoints, new AerialDistanceComparator(startingPoint));	//punto più vicino che soddisfa da solo la richiesta
			DistanceCalculator dc = new DistanceCalculator();
			double maxDistance = dc.get2PointsDistanceInKm(startingPoint, nearestExhaustivePoint);
			validPoints = Lambda.filter(new DistanceMatcher(startingPoint, maxDistance), points);	//punti dentro il raggio del punto più vicino che soddisfa da solo la richiesta			
		} else {	//there's no point who fulfills the quantity request
			validPoints = Lambda.filter(new DistanceMatcher(startingPoint, MAX_DISTANCE), points);	//punti entro un'area di raggio teorico massimo
		}
		EligibleRoutesCombinator erc = new EligibleRoutesCombinator(productName, quantity);
		return erc.getEligibles(startingPoint, validPoints);
	}

	private List<Point> route2points(Route percorso) {
		List<Point> points = new ArrayList<Point>();
		for (PointWithDistance pt : percorso.getPoints()) {
			Point p = new Point(pt.getLatitude(), pt.getLongitude(), pt.getId());
			p.setProducts(pt.getProducts());
			points.add(p);
		}
		return points;
	}

	private Route getValidRoute(Point from, List<Point> toVisit, boolean enableAPIs2) {
		Route route = new Route();
		toVisit = this.removePointById(this.startingPoint.getId(), toVisit);
		PointWithDistance point = this.getNearest(from, toVisit, enableAPIs);
		while (point != null) {
			route.add(point);
			toVisit = this.removePointById(point.getId(), toVisit);
			point = this.getNearest(point, toVisit, enableAPIs);
		}
		return route;
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
		List<PointWithDistance> points2 = SortPoints.sortByDistance(from, points, enableAPIs);
		if (points.isEmpty())
			return null;
		return points2.get(0);
	}

}
