package servlet;

public class DistanceParser {
	
	public static double getDistance(String json){
		System.out.println(json);
		System.out.println("---");
		json = json.substring(json.indexOf("distance="));
		String dist = json.substring(json.indexOf("text=")+5, json.indexOf(",")-2);
		return Double.parseDouble(dist);
	}
}
