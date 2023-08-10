package com.cts.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Problem3 {
	public static void main(String[] args) {
		try {
			// Read JSON file as a string
			String json = new String(Files.readAllBytes(Paths.get("Sample.Json")));

			// Parse JSON string to JsonNode
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(json);

			// Retrieve all key-value pairs from the JSON
			Map<String, JsonNode> nodeMap = mapper.convertValue(rootNode, Map.class);

			// Find the first node value with only numbers
			String nodeName = nodeMap.entrySet().stream().filter(entry -> isNumeric(entry.getValue()))
					.map(Map.Entry::getKey).findFirst().orElse("");

			// Write the node name to a text file
			if (!nodeName.isEmpty()) {
				Files.write(Paths.get("json-output.txt"), nodeName.getBytes());
				System.out.println("Node name written to output.txt");
			} else {
				System.out.println("No matching node found");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean isNumeric(JsonNode value) {
		if (value.isNumber()) {
			return true;
		} else if (value.isTextual()) {
			String text = value.asText();
			return text.matches("[0-9]+");
		}
		return false;
	}
}
