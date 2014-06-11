package actions;

import java.util.ArrayList;
import java.util.List;

import resources.Dataset;
import resources.Punto;
import resources.PuntoDist;
import servlet.OrdinaPunti;

public class ShortestRouteAction {

	private String prodotto;
	private int quantita;

	public ShortestRouteAction(String prodotto, int quantita) {
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	public List<Punto> getRoute() {
		List<Punto> route = new ArrayList<Punto>();
		for (PuntoDist p : this.getPercorso()) {
			Punto pt = new Punto(p.getLatitudine(), p.getLongitudine(),
					p.getId());
			pt.setProdotti(p.getProdotti());
			route.add(pt);
		}
		return route;
	}

	public List<PuntoDist> getPercorso() {
		List<PuntoDist> route = new ArrayList<PuntoDist>();
		List<Punto> points = Dataset.getPuntiByProdotto(prodotto);
		System.out.println("SIZE1: "+points.size());
		PuntoDist corrente = this.getPiuVicino(Dataset.getPuntoUtente(),points);
		int currentQuantity = corrente.searchProdotto(prodotto).getQuantità();
		if (currentQuantity >= this.quantita) {
			route.add(corrente);
		} else {
			while(currentQuantity < this.quantita){
				route.add(corrente);
				points.remove(corrente);
				System.out.println("SIZE2 "+points.size());
				corrente = this.getPiuVicino(corrente, points);
			}

		}
		return route;
	}

	public PuntoDist getPiuVicino(Punto from, List<Punto> punti) {
		return OrdinaPunti.ordinaPerDistanza(from, punti).get(0);
	}
}
