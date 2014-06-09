package resources;

public class Prodotto {
	private String prodotto;
	private int quantità;
	private double prezzo;
	
	public Prodotto(String prodotto, int quantità, double prezzo){
		this.quantità = quantità;
		this.prezzo = prezzo;
		this.prodotto = prodotto;
	}

	public String getProdotto() {
		return prodotto;
	}

	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
}
