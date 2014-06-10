package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import actions.BestPriceAction;

import com.google.gson.Gson;

import resources.Dataset;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json");
		String product = req.getParameter("product");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		String searchMethod = req.getParameter("search_method");
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		
		switch(searchMethod){
		case "best price":{
			BestPriceAction bpa = new BestPriceAction(product, quantity);
			out.println(gson.toJson(bpa.getRoute()));
			break;
		}
		case "shortest rute":{
			CalcolaPercorsoBreve cpb = new CalcolaPercorsoBreve(Dataset.getPuntoUtente(),product, quantity,0,0, Dataset.getPuntiByProdotto(product));
			out.println(gson.toJson(cpb.getPercorso()));
			break;
		}
		}

	}
}
