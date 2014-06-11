package resources;

import java.util.Comparator;

public class PriceComparator implements Comparator<Punto> {

	private String product;
	
	public PriceComparator(String productName){
		this.product = productName;
	}
	@Override
	public int compare(Punto p1, Punto p2) {
		double price1 = p1.searchProdotto(product).getPrezzo();
		double price2 = p2.searchProdotto(product).getPrezzo();
		return Double.compare(price1, price2);
	}
}
