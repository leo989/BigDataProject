package resources;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dataset {
	private static LinkedList<Point> POINTS;
	private static Point userPoint = new Point(42.460559, 12.386838,0);
		
	public static LinkedList<Point> getAllPoints(){
		if(POINTS != null)
			return POINTS;
		else{
			return generatePoints();
		}
	}
	
	private static LinkedList<Point> generatePoints(){
		POINTS = new LinkedList<Point>();
		//roma
		Point p = new Point(41.884899,12.478988,1);
		p.addProduct(new Product("gamberi",10,1));
		p.addProduct(new Product("soiole",20, 0.5));
		POINTS.add(p);
		//civitavecchia
		p = new Point(42.094911,11.79517,2);
		p.addProduct(new Product("gamberi",15,1));
		p.addProduct(new Product("soiole",15,2));
		p.addProduct(new Product("cozze",100,0.1));
		POINTS.add(p);
		//napoli
		p = new Point(40.857448,14.268097,3);
		p.addProduct(new Product("gamberi",15,1));
		p.addProduct(new Product("soiole",15,2));
		p.addProduct(new Product("cozze",300,0.2));
		POINTS.add(p);
		//orbetello
		p = new Point(42.441131,11.21265,4);
		p.addProduct(new Product("gamberi",30,1.5));
		p.addProduct(new Product("soiole",10,3));
		p.addProduct(new Product("vongole",200,1));
		POINTS.add(p);
		//montalto di castro
		p = new Point(42.354537, 11.606610,5);
		p.addProduct(new Product("gamberi",30,1.5));
		p.addProduct(new Product("soiole",10,3));
		p.addProduct(new Product("vongole",200,1));
		POINTS.add(p);
		//tarquinia
		p = new Point(42.260106, 11.757795,6);
		p.addProduct(new Product("gamberi",30,1.5));
		p.addProduct(new Product("vongole",200,1.2));
		POINTS.add(p);
		//monteromano
		p = new Point(42.258073, 11.896840,7);
		p.addProduct(new Product("trote",10,2));
		p.addProduct(new Product("vongole",200,1.2));
		POINTS.add(p);
		//ladispoli
		p = new Point(41.957163, 12.076302,8);
		p.addProduct(new Product("gamberi",100,0.8));
		p.addProduct(new Product("vongole",10,3));
		p.addProduct(new Product("spigole",15,5));
		POINTS.add(p);
		//anzio
		p = new Point(41.461290, 12.621904,9);
		p.addProduct(new Product("gamberi",100,0.8));
		p.addProduct(new Product("spigole",15,5));
		POINTS.add(p);
		//terracina
		p = new Point(41.310915, 13.232832,10);
		p.addProduct(new Product("spigole",15,5));
		p.addProduct(new Product("soiole",200,3));
		POINTS.add(p);
		//montefiascone
		p = new Point(42.556173, 12.035764,11);
		p.addProduct(new Product("trote",200,0.8));
		p.addProduct(new Product("lattarino",400,1));
		POINTS.add(p);
		//viterbo
		p = new Point(42.430835, 12.107520,12);
		p.addProduct(new Product("gamberi",50,2));
		p.addProduct(new Product("lattarino",100,2));
		POINTS.add(p);
		//bracciano
		p = new Point(42.101653, 12.174316,13);
		p.addProduct(new Product("trote",100,1.5));
		p.addProduct(new Product("soiole",200,2.5));
		POINTS.add(p);
		//vetralla
		p = new Point(42.316908, 12.058074,14);
		p.addProduct(new Product("gamberi",80,3));
		p.addProduct(new Product("cozze",100,3));
		POINTS.add(p);
		//soriano nel cimino
		p = new Point(42.427779, 12.236783,15);
		p.addProduct(new Product("soiole",50,2));
		p.addProduct(new Product("vongole",70,5));
		POINTS.add(p);
		//marta
		p = new Point(42.535287, 11.925151,16);
		p.addProduct(new Product("trote",100,3));
		p.addProduct(new Product("lattarino",300,0.5));
		POINTS.add(p);
		//anguillara
		p = new Point(42.090850, 12.271623,17);
		p.addProduct(new Product("trote",200,4));
		p.addProduct(new Product("lattarino",150,2));
		POINTS.add(p);
		
		
		
		return POINTS;
	}
	
	public static List<Point> getPointByProduct(String productName){
		List<Point> ris = new ArrayList<Point>();
		for (Point p: getAllPoints()) {
			if (p.getProduct(productName) != null)
				ris.add(p);
		}
		return ris;
	}

	public static Point getUserPoint() {
		return userPoint;
	}

	public static void setUserPoint(Point userPoint) {
		Dataset.userPoint = userPoint;
	}
}
