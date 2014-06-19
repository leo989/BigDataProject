package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resources.Dataset;
import resources.Point;
import actions.ShortestRouteAction;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class TSPSearchServlet extends HttpServlet {
	private String product;
	private int quantity;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		this.product = req.getParameter("product");
		this.quantity = Integer.parseInt(req.getParameter("quantity"));
		String check = req.getParameter("APIs");
		boolean enableAPIs = (check != null);
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();

		List<Point> pointsToVisit = this.searchValidPoints(Dataset
				.getPointByProduct(product));
	
		ShortestRouteAction sra = new ShortestRouteAction(product, quantity, Dataset.getUserPoint(), enableAPIs);
		out.println(gson.toJson(sra.getRoute()));
	}

	private List<Point> searchValidPoints(List<Point> pointByProduct) {
		List<Point> pointsToReturn = new ArrayList<Point>();
		Point migliore = this.theFirstBest(pointByProduct);
		if (migliore != null) {
			for (Point p : pointByProduct) {
				if (this.getAirDistance(Dataset.getUserPoint(), migliore) > this
						.getAirDistance(Dataset.getUserPoint(), p)) {
					migliore = p;
				}
			}
			double maxDist = this.getAirDistance(Dataset.getUserPoint(), migliore);
			for(Point p: pointByProduct){
				if(this.validePoint(p,maxDist))
					pointsToReturn.add(p);
			}
			return pointsToReturn;
		}else{
			return null;
		}

	}

	private boolean validePoint(Point p, double maxDist) {
		return this.getAirDistance(Dataset.getUserPoint(), p) <= maxDist;
	}

	private double getAirDistance(Point p1, Point p2) {
		return Math
				.sqrt((Math.pow(
						(p1.getLatitude() - p2.getLatitude()), 2))
						+ (Math.pow((p1.getLongitude() - p2
								.getLongitude()), 2)));
	}

	private Point theFirstBest(List<Point> pointByProduct) {
		for (Point p : pointByProduct) {
			if (p.getProduct(this.product).getQuantity() >= this.quantity)
				return p;
		}
		return null;
	}
}
