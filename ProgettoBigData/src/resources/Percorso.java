package resources;

import java.util.ArrayList;
import java.util.List;

public class Percorso {
	private List<PuntoDist> percorso;
	private double lunghezza;
	private int quantity;
	
	public Percorso(){
		this.percorso = new ArrayList<PuntoDist>();
		this.quantity = 0;
	}
	
	public List<PuntoDist> getPercorso() {
		return percorso;
	}
	public void setPercorso(List<PuntoDist> percorso) {
		this.percorso = percorso;
	}
	public double getLunghezza() {
		return lunghezza;
	}
	public void setLunghezza(double lunghezza) {
		this.lunghezza = lunghezza;
	}

	public void add(PuntoDist corrente) {
		this.percorso.add(corrente);
		this.lunghezza += corrente.getDist();	
	}

	@Override
	public String toString() {
		String toString = "";
		for(PuntoDist p: this.percorso)
			toString += ("-> "+p.getId());
		return toString+" Lunghezza totale "+this.lunghezza;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void aggQuantity(int q){
		this.quantity += q;
	}
	
	
	
}
