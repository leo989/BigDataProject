package resources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Point {
	double latitude;
	private double longitude;
	private Map<String, Product> products;
	private long id;
	
	
	public Point(double latitude, double longitude, long id){
		this.setId(id);
		this.latitude = latitude;
		this.longitude = longitude;
		this.products = new HashMap<String, Product>();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public Map<String, Product> getProducts() {
		return products;
	}

	public void setProducts(Map<String, Product> products) {
		this.products = products;
	}
	public void addProduct(Product product){
		this.products.put(product.getName(), product);
	}
	
	public Product getProduct(String productName){
		return products.get(productName);
	}

	@Override
	public int hashCode() {
		return (int) Double.doubleToLongBits(this.latitude + this.longitude);
	}

	@Override
	public boolean equals(Object obj) {
		Point other = (Point) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
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
