package resources;

import java.util.LinkedList;
import java.util.List;

public class Punto {
	double latitudine;
	private double longitudine;
	private List<Prodotto> prodotti;
	
	public Punto(double latitudine, double longitudine){
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.prodotti = new LinkedList<Prodotto>();
	}

	public double getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(long latitudine) {
		this.latitudine = latitudine;
	}

	public double getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(long longitudine) {
		this.longitudine = longitudine;
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	public void addProdotto(Prodotto prodotto){
		this.prodotti.add(prodotto);
	}
	
	public Prodotto searchProdotto(String prodotto){
		for(Prodotto p: this.prodotti){
			if(p.getProdotto().equals(prodotto))
				return p;
		}
		return null;
	}
}
