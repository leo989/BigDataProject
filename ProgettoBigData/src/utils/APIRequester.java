package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;

import resources.Point;

public class APIRequester {
	private String APIUrl = "http://maps.googleapis.com/maps/api/directions/json?";
	private Gson gson;
	
	public APIRequester(){
		gson = new Gson();
	}
	
	public JSONObject request(Point start, Point end) {
		try {
			String origin = start.getLatitude()+","+start.getLongitude();
			String destination = end.getLatitude()+","+end.getLongitude();
			HttpTransport httpTransport = new NetHttpTransport();
			HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
			JSONParser parser = new JSONParser();
			GenericUrl url = new GenericUrl("http://maps.googleapis.com/maps/api/directions/json");
			url.put("origin", origin);
			url.put("destination", destination);
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse httpResponse = request.execute();
			return (JSONObject) parser.parse(httpResponse.parseAsString()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
