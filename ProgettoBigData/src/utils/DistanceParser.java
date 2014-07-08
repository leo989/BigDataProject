package utils;

import org.json.simple.JSONObject;

public class DistanceParser {
	
	public static double getDistance(JSONObject json) {
		String jsonString = json.toJSONString();
		jsonString = jsonString.substring(jsonString.indexOf("\"distance\":"));
		String dist = jsonString.substring(jsonString.indexOf("\"text\":\"")+8, jsonString.indexOf(",")-3);
		return Double.parseDouble(dist);
	}
}
