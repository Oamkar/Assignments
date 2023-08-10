package com.cts.main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.cts.main.model.Booking;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Problem5 {
	
	public void execute() {
		getBookingIds();
		//createBooking();
	}
	
	private void getBookingIds() {

		try {
			URL url = new URL("https://restful-booker.herokuapp.com/booking");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) { // If the request was successful
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuilder response = new StringBuilder();

				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				reader.close();

				System.out.println(response.toString());
			} else {
				System.out.println("GET request failed. Response Code: " + responseCode);
			}

			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createBooking() {
		try {
			// Create a URL object with the desired URL
			URL url = new URL("https://restful-booker.herokuapp.com/booking");

			// Open a connection to the URL
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set the request method to POST
			connection.setRequestMethod("POST");

			// Enable input and output streams
			connection.setDoOutput(true);
			connection.setDoInput(true);

			// Set the content type and length headers
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", String.valueOf(10)); // Adjust content length

			// Create an instance of ObjectMapper from Jackson library
            ObjectMapper objectMapper = new ObjectMapper();
            
            // Read the JSON file and map its contents to the Java POJO class
            Booking booking = objectMapper.readValue(new File("booking-data.json"), Booking.class);

			// Write the data to the output stream
			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.write(objectMapper.writeValueAsBytes(booking));
			outputStream.flush();
			outputStream.close();

			// Get the response code
			int responseCode = connection.getResponseCode();
			System.out.println("Response Code: " + responseCode);

			// Read the response from the input stream
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Print the response
			System.out.println("Response: " + response.toString());

			// Disconnect the connection
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
