package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resources.Dataset;
import resources.Punto;
import actions.BestPriceAction;
import actions.ShortestRouteAction2;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String product = req.getParameter("product");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String searchMethod = req.getParameter("search_method");
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		
		switch(searchMethod.toLowerCase()){
		case "best price":{
			BestPriceAction bpa = new BestPriceAction(product, quantity);
			out.println(gson.toJson(bpa.getRoute()));
			break;
		}
		case "shortest route":{
			System.out.println("SHORTEST ROUTE");
			ShortestRouteAction2 sra2 = new ShortestRouteAction2(product, quantity, Dataset.getPuntoUtente());
			List<Punto> points = sra2.getRoute();
			out.println(gson.toJson(points));
			break;
		}
		}

	}
}
