package com.rgt.engine;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIDriver {
	static StringBuilder responseContent = new StringBuilder();
	static List<String> response;
	static HttpURLConnection connection;
	static URL url;
	static int responseCode;

	public static List<String> getRequest(String URL) {
		response = new ArrayList<String>();
		String apiUrl = "https://jsonplaceholder.typicode.com/posts";
		
		try {
			url = new URL(apiUrl);
			// Open a connection to the URL
			connection = (HttpURLConnection) url.openConnection();
			// Set the request method (GET)
			connection.setRequestMethod("GET");
			// connection.setRequestProperty("Authorization", "Bearer YourAccessToken");
			connection.setRequestProperty("Content-Type", "application/json");
			responseCode = connection.getResponseCode();
			// Read and print the response content
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				responseContent.append(line);
			}
			
			response.add(Integer.toString(responseCode));
			response.add(responseContent.toString());
			reader.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public static List<String> postRequest() {
		response = new ArrayList<String>();;
		String apiUrl = "https://jsonplaceholder.typicode.com/posts";
		
		try {

			// Create a URL object
			url = new URL(apiUrl);
			// Open a connection to the URL
			connection = (HttpURLConnection) url.openConnection();
			// Set the request method to POST
			connection.setRequestMethod("POST");
			// Set request headers
			// connection.setRequestProperty("Authorization", "Bearer YourAccessToken");
			connection.setRequestProperty("Content-Type", "application/json");
			// Enable input and output streams for writing the request body
			connection.setDoOutput(true);
			// Define the JSON payload
			String jsonPayload = "{\r\n" + "    \"userId\": 1000,\r\n" + "    \"it\": 1,\r\n"
					+ "    \"title\": \"API Testing\",\r\n"
					+ "    \"body\": \"because he also accepts\\nundertakes the consequences of refusal and when\\nhe criticizes the troubles so that the whole\\nof our things are but they are the matter will happen to the architect\"\r\n"
					+ "  }";
			// Write the JSON payload to the request body
			try (DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream())) {
				dataOutputStream.writeBytes(jsonPayload);
				dataOutputStream.flush();
			}
			// Get the response code
			responseCode = connection.getResponseCode();
			// Read and print the response content
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder responseContent = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				responseContent.append(line);
			}
			response.add(Integer.toString(responseCode));
			response.add(responseContent.toString());
			reader.close();
			// Close the connection
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
