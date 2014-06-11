package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import actions.BestPriceAction;
import actions.ShortestRouteAction;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String product = req.getParameter("product");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String searchMethod = req.getParameter("search_method");
//		String product = req.getParameter("prodotto");
//		int quantity = Integer.parseInt(req.getParameter("quantita"));
//		String searchMethod = "shortest route";
		
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
			ShortestRouteAction sra = new ShortestRouteAction(product, quantity);
			out.println(gson.toJson(sra.getRoute()));
			break;
		}
		}

	}
}
