package prova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import resources.Punto;

public class APIRequester {
	private String APIUrl = "http://maps.googleapis.com/maps/api/directions/json?";
	private Gson gson;
	
	public APIRequester(){
		gson = new Gson();
	}
	public String request(Punto origine, Punto destinazione) throws IOException{
		String origin = "origin="+origine.getLatitudine()+","+origine.getLongitudine();
		String destination = "destination="+destinazione.getLatitudine()+","+destinazione.getLongitudine();
		URL url = new URL(APIUrl+origin+"&"+destination);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String json = gson.fromJson(read, Object.class).toString();
		return json;		
	}
}
