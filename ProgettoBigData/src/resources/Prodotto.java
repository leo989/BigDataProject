package resources;

public class Prodotto {
	private String prodotto;
	private int quantit�;
	private double prezzo;
	
	public Prodotto(String prodotto, int quantit�, double prezzo){
		this.quantit� = quantit�;
		this.prezzo = prezzo;
		this.prodotto = prodotto;
	}

	public String getProdotto() {
		return prodotto;
	}

	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}

	public int getQuantit�() {
		return quantit�;
	}

	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
}
