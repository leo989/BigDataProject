package resources;

import java.util.ArrayList;
import java.util.List;

public class FilterPoint {
	public static List<Point> filter(List<Point> pointByProduct, String nameProduct, double quantity) {
		List<Point> pointsToReturn = new ArrayList<Point>();
		Point migliore = theFirstBest(pointByProduct, nameProduct, quantity);
		if (migliore != null) {
			for (Point p : pointByProduct) {
				if (getAirDistance(Dataset.getUserPoint(), migliore) > getAirDistance(Dataset.getUserPoint(), p)) {
					migliore = p;
				}
			}
			double maxDist = getAirDistance(Dataset.getUserPoint(), migliore);
			System.out.println("DIST MAX "+maxDist);
			for(Point p: pointByProduct){
				if(validePoint(p,maxDist))
					pointsToReturn.add(p);
			}
			return pointsToReturn;
		}else{
			for(Point p: pointByProduct){
				if(validePoint(p,2))
					pointsToReturn.add(p);
			}
			return pointsToReturn;
		}

	}

	private static boolean validePoint(Point p, double maxDist) {
		return getAirDistance(Dataset.getUserPoint(), p) <= maxDist;
	}

	private static double getAirDistance(Point p1, Point p2) {
		double ris = Math
				.sqrt((Math.pow(
						(p1.getLatitude() - p2.getLatitude()), 2))
						+ (Math.pow((p1.getLongitude() - p2
								.getLongitude()), 2)));
		return ris;
	}

	private static Point theFirstBest(List<Point> pointByProduct, String product, double quantity) {
		for (Point p : pointByProduct) {
			if (p.searchProduct(product).getQuantity() >= quantity){
				return p;
			}
		}
		return null;
	}
}
