package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
		String product = req.getParameter("product");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		double kmAlLitro;
		try{
			kmAlLitro = Double.parseDouble(req.getParameter("kilometers-per-liter"));
		}catch(Exception e){
			kmAlLitro =16;
		}
		double euroAlLitro;
		try{
			euroAlLitro = Double.parseDouble(req.getParameter("euros-per-liter"));
		}catch(Exception e){
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
			BestPriceAction bpa = new BestPriceAction(product, quantity);
			out.println(gson.toJson(bpa.getRoute()));
			break;
		}
		case "2":{
			ShortestRouteAction sra2 = new ShortestRouteAction(product, quantity, Dataset.getUserPoint(), enableAPIs);
			List<Point> points = sra2.getRoute(Dataset.getPointByProduct(product));
			out.println(gson.toJson(points));
			break;
		}
		case "3":{
			BestTotalCostAction btca = new BestTotalCostAction(product, quantity, Dataset.getUserPoint(),kmAlLitro,euroAlLitro, enableAPIs);
			List<Point> points = btca.getRoute();
			out.println(gson.toJson(points));
			break;
		}
		}

	}
}
