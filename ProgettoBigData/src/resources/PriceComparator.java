package resources;

import java.util.Comparator;

public class PriceComparator implements Comparator<Point> {

	private String product;
	
	public PriceComparator(String productName){
		this.product = productName;
	}
	@Override
	public int compare(Point p1, Point p2) {
		double price1 = p1.searchProduct(product).getPrice();
		double price2 = p2.searchProduct(product).getPrice();
		return Double.compare(price1, price2);
	}
}
