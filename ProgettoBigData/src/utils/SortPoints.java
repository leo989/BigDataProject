package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import resources.DistanceMatrix;
import resources.Point;
import resources.PointWithDistance;

public class SortPoints {
	
	public static List<PointWithDistance> sortByDistance(Point from, List<Point> points, boolean enableAPIs) {
		List<PointWithDistance> ris;
		try {
			ris = calculateDistance(from, points, enableAPIs);
			Collections.sort(ris);
			return ris;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<Point> sortByDistanceAndReturnPoint(Point from, List<Point> points, boolean enableAPIs) {
		List<Point> ris = new ArrayList<Point>();
		List<PointWithDistance> pointsWithDistance = null;
		try {
			pointsWithDistance = calculateDistance(from, points, enableAPIs);
			if (pointsWithDistance == null)
				return null;
			Collections.sort(pointsWithDistance);
			for(PointWithDistance pt: pointsWithDistance){
				Point p = new Point(pt.getLatitude(),pt.getLongitude(),pt.getId());
				p.setProducts(pt.getProducts());
				ris.add(p);
			}
			return ris;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<PointWithDistance> calculateDistance(Point from, List<Point> points, boolean enableAPIs) throws IOException, ParseException {
		List<PointWithDistance> pointsWithDistance = new ArrayList<PointWithDistance>();
		PointWithDistance point;
		for (Point p : points) {
			if (!from.equals(p)) {
				double dist;
				if (enableAPIs) {
					APIRequester apir = new APIRequester();
					JSONObject json = apir.request(from, p);
					String error = (String) json.get("error_message");
					while (error != null && !error.isEmpty() && error.equals("You have exceeded your rate-limit for this API.")) {
						System.out.println("Sleeping 3 sec...zzZZzzZZ...");
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						json = apir.request(from, p);
						error = (String) json.get("error_message");
					}
					dist = DistanceParser.getDistance(json);
				} else {
					dist = DistanceMatrix.getDistance(from.getId(), p.getId());
				}
				if (dist != 0) {
					point = new PointWithDistance(p.getLatitude(), p.getLongitude(), p.getId());
					point.setDist(dist);
					point.setProducts(p.getProducts());
					pointsWithDistance.add(point);
				}
			}
		}
		return pointsWithDistance;
	}
}
