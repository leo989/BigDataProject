package prova;

import java.util.LinkedList;
import java.util.List;

import resources.Dataset;
import resources.Prodotto;
import resources.PuntoDist;

public class CalcolaPercorsoBreve {
	String prodotto;
	int quantita;

	public CalcolaPercorsoBreve(String prodotto, int quantita) {
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	public List<PuntoDist> getPercorso() {
		List<PuntoDist> ret = new LinkedList<PuntoDist>();
		List<PuntoDist> puntiOrdinati = OrdinaPunti.ordinaPerDistanza(Dataset
				.getPuntiByProdotto(prodotto));
		PuntoDist pt = puntiOrdinati.get(0);
		Prodotto pr = pt.searchProdotto(prodotto);
		if (pr.getQuantità() >= this.quantita) {
			ret.add(puntiOrdinati.get(0));
			return ret;
		} else {
			return ret;
		}
	}
}
