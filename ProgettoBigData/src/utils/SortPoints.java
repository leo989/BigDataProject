package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<Point> sortByDistanceAndReturnPoint(Point from, List<Point> points, boolean enableAPIs) {
		List<Point> ris = new ArrayList<Point>();
		List<PointWithDistance> pointsWithDistance;
		try {
			pointsWithDistance = calculateDistance(from, points, enableAPIs);
			Collections.sort(pointsWithDistance);
			for(PointWithDistance pt: pointsWithDistance){
				Point p = new Point(pt.getLatitude(),pt.getLongitude(),pt.getId());
				p.setProducts(pt.getProducts());
				ris.add(p);
			}
			return ris;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<PointWithDistance> calculateDistance(Point from, List<Point> points, boolean enableAPIs) throws IOException {
		List<PointWithDistance> pointsWithDistance = new ArrayList<PointWithDistance>();
		PointWithDistance point;
		for (Point p : points) {
			if (!from.equals(p)) {
				double dist;
				if (enableAPIs) {
					APIRequester apir = new APIRequester();
					String json = apir.request(from, p);
					System.out.println(json);
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
