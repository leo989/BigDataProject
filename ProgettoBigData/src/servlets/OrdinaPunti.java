package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import resources.DistanceMatrix;
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
	public static List<Punto> ordinaPerDistanzaAndReturnPunto(Punto from, List<Punto> punti) {
		List<Punto> ris = new ArrayList<Punto>();
		List<PuntoDist> points;
		try {
			points = calcolaDist(from, punti);
			Collections.sort(points);
			for(PuntoDist pt: points){
				Punto p = new Punto(pt.getLatitudine(),pt.getLongitudine(),pt.getId());
				p.setProdotti(pt.getProdotti());
				ris.add(p);
			}
			return ris;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<PuntoDist> calcolaDist(Punto from, List<Punto> punti)
			throws IOException {
		List<PuntoDist> puntiDist = new ArrayList<PuntoDist>();
		PuntoDist punto;
		for (Punto p : punti) {
			if (!from.equals(p)) {
//				APIRequester apir = new APIRequester();
//				String json = apir.request(from, p);
				double dist = DistanceMatrix.getDistance(from.getId(), p.getId());
				if (dist != 0) {
					punto = new PuntoDist(p.getLatitudine(), p.getLongitudine(), p.getId());
					punto.setDist(dist);
					punto.setProdotti(p.getProdotti());
					puntiDist.add(punto);
				}
			}
		}
		return puntiDist;
	}
}
