package actions;

import java.util.ArrayList;
import java.util.List;

import resources.Dataset;
import resources.Percorso;
import resources.Punto;
import resources.PuntoDist;
import servlets.OrdinaPunti;

public class ShortestRouteAction {

	private String product;
	private int quantity;
	private List<PuntoDist> points;
	private List<PuntoDist> points1;

	public ShortestRouteAction(String product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public List<Punto> getRoute() {
		List<Punto> points = new ArrayList<Punto>();
		for (PuntoDist p : this.getMigliore(Dataset.getPuntoUtente()).getPercorso()) {
			Punto pt = new Punto(p.getLatitudine(), p.getLongitudine(), p.getId());
			pt.setProdotti(p.getProdotti());
			points.add(pt);
		}
		return points;
	}

	private Percorso getMigliore(Punto utente){
		this.points1 = OrdinaPunti.ordinaPerDistanza(utente,Dataset.getPuntiByProdotto(product));
		Percorso percorso1 = this.getPercorso(utente);
		System.out.println("P1: "+percorso1);
		Percorso percorso2;
		int i = 1;
		while(i<points1.size()){
			percorso2 = getPercorso(points1.get(i));
			System.out.println("P2: "+percorso2);
			if(percorso1.getLunghezza()>percorso2.getLunghezza())
				percorso1 = percorso2;
			i++;
		}
		return percorso1;
		
	}
	
	public Percorso getPercorso(Punto from) {
		Percorso percorso = new Percorso();
		List<Punto> points = Dataset.getPuntiByProdotto(product);
		PuntoDist corrente = this.getPiuVicino(from, points);
		int currentQuantity = corrente.searchProdotto(this.product).getQuantità();
		percorso.add(corrente);
		while(currentQuantity < this.quantity){
			points.remove(corrente);
			corrente = this.getPiuVicino(corrente, points);
			if (corrente == null) 
				return percorso;
			currentQuantity += corrente.searchProdotto(this.product).getQuantità();
			percorso.add(corrente);			
		}
		return percorso;
		
		
	}

	public PuntoDist getPiuVicino(Punto from, List<Punto> punti) {
		points = OrdinaPunti.ordinaPerDistanza(from, punti);
		if (points == null || points.isEmpty())
			return null;
		return points.get(0);
	}
}
