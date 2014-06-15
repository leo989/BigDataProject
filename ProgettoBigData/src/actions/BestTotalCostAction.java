package actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import resources.CostRouteComparator;
import resources.Dataset;
import resources.DistanceMatrix;
import resources.Percorso;
import resources.Punto;
import resources.PuntoDist;
import resources.RouteComparator;
import servlets.OrdinaPunti;

public class BestTotalCostAction {
	private String product;
	private double quantity;
	private Punto utente;
	private double kmWeight;

	public BestTotalCostAction(String product, int quantity, Punto puntoUtente, double kmAlLitro, double euroAlLitro) {
		this.product = product;
		this.quantity = quantity;
		this.utente = puntoUtente;
		this.kmWeight = euroAlLitro/kmAlLitro;
	}

	public List<Punto> getRoute() {
		List<Punto> totalPoints = OrdinaPunti.ordinaPerDistanzaAndReturnPunto(utente, Dataset.getPuntiByProdotto(product));
		List<Percorso> totalRoute = new ArrayList<Percorso>();
		while(totalPoints.size()>0){
			Percorso route = this.getValidRoute(utente, totalPoints);
			PuntoDist userPoint = new PuntoDist(utente.getLatitudine(),utente.getLongitudine(),utente.getId());
			userPoint.setDist(DistanceMatrix.getDistance(route.getPercorso().get(route.getPercorso().size()-1).getId(), utente.getId()));
			route.add(userPoint);
			route.aggTotalCost(product, quantity, kmWeight);
			totalRoute.add(route);
			totalPoints.remove(0);
		}
		Collections.sort(totalRoute, new CostRouteComparator());
		return this.route2points(totalRoute.get(0));
	}

	private List<Punto> route2points(Percorso percorso) {
		List<Punto> points = new ArrayList<Punto>();
		for(PuntoDist pt: percorso.getPercorso()){
			Punto p = new Punto(pt.getLatitudine(), pt.getLongitudine(), pt.getId());
			p.setProdotti(pt.getProdotti());
			points.add(p);
		}
		return points;
	}

	private Percorso getValidRoute(Punto from, List<Punto> toVisit) {
		Percorso route = new Percorso();
		PuntoDist point = this.getPiuVicino(from, toVisit);
		if(point != null){
			route.add(point);
			route.aggQuantity(point.searchProdotto(product).getQuantità());
			while (route.getQuantity() < this.quantity) {
				List<Punto> toVisitBis = this.removePointById(point.getId(),toVisit);
				point = this.getPiuVicino(point, toVisitBis);
				if(point!=null){
					route.add(point);
					route.aggQuantity(point.searchProdotto(product).getQuantità());
					toVisit = toVisitBis;
				}else{
					return route;
				}
			}
		return route;
		}else{
			return route;
		}
	}

	private List<Punto> removePointById(long id, List<Punto> punti) {
		List<Punto> points = new ArrayList<Punto>();
		for (Punto p : punti) {
			if (p.getId() != id)
				points.add(p);
		}
		return points;
	}

	private PuntoDist getPiuVicino(Punto from, List<Punto> punti) {
		List<PuntoDist> points = OrdinaPunti.ordinaPerDistanza(from, punti);
		if (points.isEmpty())
			return null;
		return points.get(0);
	}

}
