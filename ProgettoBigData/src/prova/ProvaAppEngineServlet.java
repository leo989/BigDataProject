package prova;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resources.Dataset;
import resources.PuntoDist;

@SuppressWarnings("serial")
public class ProvaAppEngineServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/json");
		String prodotto = req.getParameter("prodotto");
		int quantita = Integer.parseInt(req.getParameter("quantita"));
		PrintWriter out = resp.getWriter();
		CalcolaPercorsoBreve cpb = new CalcolaPercorsoBreve(prodotto, quantita);
		for(PuntoDist p: cpb.getPercorso()){
			out.println(p.getDist());
		}
	}
}
