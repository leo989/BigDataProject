package resources;

import java.util.LinkedList;
import java.util.List;

public class Punto {
	double latitudine;
	private double longitudine;
	private List<Prodotto> prodotti;
	private long id;
	
	
	public Punto(double latitudine, double longitudine, long id){
		this.setId(id);
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

	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		long temp;
//		temp = Double.doubleToLongBits(latitudine);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(longitudine);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		return result;
		return (int) Double.doubleToLongBits(this.latitudine + this.longitudine);
	}

	@Override
	public boolean equals(Object obj) {
		Punto other = (Punto) obj;
		if (Double.doubleToLongBits(latitudine) != Double
				.doubleToLongBits(other.latitudine))
			return false;
		if (Double.doubleToLongBits(longitudine) != Double
				.doubleToLongBits(other.longitudine))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
