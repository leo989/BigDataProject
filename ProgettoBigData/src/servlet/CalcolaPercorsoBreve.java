package servlet;

import java.util.LinkedList;
import java.util.List;

import resources.Dataset;
import resources.Prodotto;
import resources.Punto;
import resources.PuntoDist;

public class CalcolaPercorsoBreve {
	String prodotto;
	int quantita;
	int quantitaParziale;
	double distParziale;
	List<PuntoDist> percorso;
	List<PuntoDist> vicini;
	Punto from;
	List<Punto> punti;

	public CalcolaPercorsoBreve(Punto from, String prodotto, int quantita,
			int quantitaParziale, double distParziale, List<Punto> punti) {
		this.prodotto = prodotto;
		this.quantita = quantita;
		this.distParziale = distParziale;
		this.quantitaParziale = quantitaParziale;
		this.percorso = new LinkedList<PuntoDist>();
		this.vicini = new LinkedList<PuntoDist>();
		this.from = from;
		this.punti = punti;
	}

	public List<PuntoDist> getPercorso() {
		System.out.println("CPB: "+punti.size());
		vicini = OrdinaPunti.ordinaPerDistanza(from,
				Dataset.getPuntiByProdotto(prodotto));
		PuntoDist pt = getPiuVicino(from, punti);
		Prodotto pr = pt.searchProdotto(prodotto);
		if ((pr.getQuantità() + this.quantitaParziale) >= this.quantita) {
			percorso.add(pt);
			return percorso;
		} else {
			punti.remove(pt);
			CalcolaPercorsoBreve cpb = new CalcolaPercorsoBreve(pt, prodotto,
					quantita, this.quantitaParziale + pr.getQuantità(),
					this.distParziale + pt.getDist(), punti);
			percorso.addAll(cpb.getPercorso());
			return percorso;
		}
	}

	public PuntoDist getPiuVicino(Punto from, List<Punto> punti) {
		return OrdinaPunti.ordinaPerDistanza(from, punti).get(0);
	}
}
