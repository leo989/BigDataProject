package prova;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import resources.Dataset;
import resources.Punto;
import resources.PuntoDist;

public class OrdinaPunti {

	public static List<PuntoDist> ordinaPerDistanza(List<Punto> punti) {
		List<PuntoDist> ris;
		try {
			ris = calcolaDist(punti);
			Collections.sort(ris);
			return ris;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private static List<PuntoDist> calcolaDist(List<Punto> punti)
			throws IOException {
		List<PuntoDist> puntiDist = new LinkedList<PuntoDist>();
		PuntoDist punto;
		for (Punto p : punti) {
			APIRequester apir = new APIRequester();
			String json = apir.request(Dataset.getPuntoUtente(), p);
			punto = new PuntoDist(p.getLatitudine(), p.getLongitudine());
			punto.setDist(DistanceParser.getDistance(json));
			punto.setProdotti(p.getProdotti());
			puntiDist.add(punto);
		}
		return puntiDist;
	}
}
