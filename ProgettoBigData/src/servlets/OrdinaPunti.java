package servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import resources.Punto;
import resources.PuntoDist;

public class OrdinaPunti {

	public static List<PuntoDist> ordinaPerDistanza(Punto from, List<Punto> punti) {
		List<PuntoDist> ris;
		try {
			ris = calcolaDist(from, punti);
			Collections.sort(ris);
			return ris;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<PuntoDist> calcolaDist(Punto from, List<Punto> punti)
			throws IOException {
		List<PuntoDist> puntiDist = new LinkedList<PuntoDist>();
		PuntoDist punto;
		for (Punto p : punti) {
			if (!from.equals(p)) {
				APIRequester apir = new APIRequester();
				String json = apir.request(from, p);
				if (DistanceParser.getDistance(json) != 0) {
					punto = new PuntoDist(p.getLatitudine(), p.getLongitudine(), p.getId());
					punto.setDist(DistanceParser.getDistance(json));
					punto.setProdotti(p.getProdotti());
					puntiDist.add(punto);
				}
			}
		}
		return puntiDist;
	}
}
