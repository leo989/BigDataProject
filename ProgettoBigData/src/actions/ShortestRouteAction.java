package actions;

import java.util.ArrayList;
import java.util.List;

import resources.Dataset;
import resources.Punto;
import resources.PuntoDist;
import servlets.OrdinaPunti;

public class ShortestRouteAction {

	private String product;
	private int quantity;

	public ShortestRouteAction(String product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public List<Punto> getRoute() {
		List<Punto> points = new ArrayList<Punto>();
		for (PuntoDist p : this.getPercorso()) {
			Punto pt = new Punto(p.getLatitudine(), p.getLongitudine(), p.getId());
			pt.setProdotti(p.getProdotti());
			points.add(pt);
		}
		return points;
	}

	public List<PuntoDist> getPercorso() {
		List<PuntoDist> route = new ArrayList<PuntoDist>();
		List<Punto> points = Dataset.getPuntiByProdotto(product);
		System.out.println("SIZE1: "+points.size());
		PuntoDist corrente = this.getPiuVicino(Dataset.getPuntoUtente(), points);
		int currentQuantity = corrente.searchProdotto(this.product).getQuantità();
		route.add(corrente);
		while(currentQuantity < this.quantity){
			points.remove(corrente);
			corrente = this.getPiuVicino(corrente, points);
			if (corrente == null) 
				return route;
			currentQuantity += corrente.searchProdotto(this.product).getQuantità();
			route.add(corrente);			
		}
		return route;
	}

	public PuntoDist getPiuVicino(Punto from, List<Punto> punti) {
		List<PuntoDist> points = OrdinaPunti.ordinaPerDistanza(from, punti);
		if (points == null || points.isEmpty())
			return null;
		return points.get(0);
	}
}
