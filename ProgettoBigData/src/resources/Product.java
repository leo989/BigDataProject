package resources;

public class Product {
	private String name;
	private int quantity;
	private double price;
	
	public Product(String name, int quantity, double price){
		this.quantity = quantity;
		this.price = price;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String productName) {
		this.name = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
