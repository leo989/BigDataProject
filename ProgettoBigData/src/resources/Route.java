package resources;

import java.util.ArrayList;
import java.util.List;

public class Route {
	private List<PointWithDistance> points;
	private double length;
	private int quantity;
	private double totalCost;
	
	public Route(){
		this.points = new ArrayList<PointWithDistance>();
		this.quantity = 0;
		this.setTotalCost(0);
	}
	
	public List<PointWithDistance> getPoints() {
		return points;
	}
	public void setPoints(List<PointWithDistance> points) {
		this.points = points;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}

	public void add(PointWithDistance currentPoint) {
		this.points.add(currentPoint);
		this.length += currentPoint.getDist();	
	}

	@Override
	public String toString() {
		String toString = "";
		for(PointWithDistance p: this.points)
			toString += (p.getId()+"->");
		toString = toString.substring(0, toString.length()-2);
		toString += " Lunghezza totale "+this.length;
		toString += " Quantita totale "+this.quantity;
		toString += " TotalCost "+this.totalCost;
		return toString;
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

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public void aggTotalCost(String product, double quantity2, double kmWeight) {
		this.totalCost += (this.length * kmWeight);
		if(this.quantity>quantity2){
			double q = quantity2;
			for(PointWithDistance p: this.points){
				Product prod = p.searchProduct(product);
				if(prod!=null){
					if(q>prod.getQuantity()){
						this.totalCost += prod.getPrice()*prod.getQuantity();
						q -= prod.getQuantity();
					}else{
						this.totalCost += prod.getPrice()*q;
					}
				}
			}
		}else{
			for(PointWithDistance p: this.points){
				Product prod = p.searchProduct(product);
				if(prod != null)
					this.totalCost += prod.getPrice()*prod.getQuantity();
			}
		}
		
	}
	
	
	
}
