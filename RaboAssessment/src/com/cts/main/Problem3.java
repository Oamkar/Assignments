package com.cts.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Problem3 {
	
	private Logger log = Logger.getLogger(Problem3.class.getName());
	
	@SuppressWarnings("unchecked")
	public void removeEmptyNodesAndWriteAllRemainingNodeNamesToNewFile() {
		try {
			
			String inputFilePath = "Sample.Json";
			String outputFilePath = "json-output.txt";
			
			// Read JSON file as a string
			String json = new String(Files.readAllBytes(Paths.get(inputFilePath)));

			// Parse JSON string to JsonNode
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(json);

			// Retrieve all key-value pairs from the JSON
			Map<String, JsonNode> nodeMap = mapper.convertValue(rootNode, Map.class);

			// Find the first node value with only numbers
			String nodeName = nodeMap.entrySet().stream().filter(entry -> Utilities.isNumeric(entry.getValue()))
					.map(Map.Entry::getKey).findFirst().orElse("");

			// Write the node name to a text file
			if (!nodeName.isEmpty()) {
				Files.write(Paths.get(outputFilePath), nodeName.getBytes());
				log.info("Node name written to "+ outputFilePath);
			} else {
				log.warn("No matching node found");
			}
		} catch (IOException e) {
			log.error("Exception occured while removing empty nodes "
					+ "and write all remaining node names to new file", e);
		}
	}
}
