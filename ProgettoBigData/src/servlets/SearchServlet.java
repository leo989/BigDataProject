package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resources.Dataset;
import resources.Point;
import actions.BestPriceAction;
import actions.BestTotalCostAction;
import actions.ShortestRouteAction;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		InputStream stream = this.getServletContext().getResourceAsStream("/WEB-INF/project.properties");
		Properties properties = new Properties();
		properties.load(stream);
		
		double startLat = Double.parseDouble(req.getParameter("startLat"));
		double startLng = Double.parseDouble(req.getParameter("startLng"));
		
		Point start = new Point(startLat, startLng, -1);
		
		String product = req.getParameter("product");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		double kmAlLitro;
		try {
			kmAlLitro = Double.parseDouble(req.getParameter("kilometers-per-liter"));
		} catch (Exception e) {
			kmAlLitro = 16;
		}
		double euroAlLitro;
		try {
			euroAlLitro = Double.parseDouble(req.getParameter("euros-per-liter"));
		} catch(Exception e) {
			euroAlLitro = 1.6;
		}
		String check = req.getParameter("APIs");
		boolean enableAPIs = (check != null);
		String searchMethod = req.getParameter("search_method");
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		
		switch(searchMethod.toLowerCase()){
		case "1":{
			BestPriceAction bpa = new BestPriceAction(start, product, quantity);
			out.println(gson.toJson(bpa.getRoute()));
			break;
		}
		case "2":{
			int maxDistance = Integer.parseInt(properties.getProperty("maxDistance"));
			int maxNumberOfPoint = Integer.parseInt(properties.getProperty("maxNumberOfPoint"));
			ShortestRouteAction sra = new ShortestRouteAction(start, product, quantity, enableAPIs, maxDistance, maxNumberOfPoint);
			List<Point> points = sra.getRoute();
			out.println(gson.toJson(points));
			break;
		}
		case "3":{
			int maxDistance = Integer.parseInt(properties.getProperty("maxDistance"));
			int maxNumberOfPoint = Integer.parseInt(properties.getProperty("maxNumberOfPointBTC"));
			BestTotalCostAction btca = new BestTotalCostAction(start, product, quantity, kmAlLitro, euroAlLitro, enableAPIs, maxDistance, maxNumberOfPoint);
			List<Point> points = btca.getRoute();
			out.println(gson.toJson(points));
			break;
		}
		}

	}
}
