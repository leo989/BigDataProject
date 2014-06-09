package prova;

public class DistanceParser {
	
	public static double getDistance(String json){
		json = json.substring(json.indexOf("distance="));
		String dist = json.substring(json.indexOf("text=")+5, json.indexOf(",")-2);
		return Double.parseDouble(dist);
	}
}
