package com.cts.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.Logger;

public class Problem1 {
	
	private Logger log = Logger.getLogger(Problem1.class.getName());
	
	/**
	 * Read the given text file and write to new file setting 
	 * First character of each sentence to Capital letter
	 */
	public void convertFirstCharacterToUpperCase(String inputFilePath, String outputFile) {

		try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

			String line;
			while ((line = br.readLine()) != null) {
				String modifiedLine = Utilities.convertFirstCharacter(line);
				bw.write(modifiedLine);
				bw.newLine();
			}
			
			log.info("First Character To UpperCase Conversion successful! Output file: "+ outputFile);
		} catch (IOException e) {
			log.error("Exception occured while converting the first character in rtf to upperCase", e);
		}
	}

	/**
	 * Read the given text file and filter the Special character and Write to a new file
	 */
	public void filterSpecialCharacters(String inputFilePath, String outputFile) {

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

			String line;
			while ((line = reader.readLine()) != null) {
				String specialCharacters = Utilities.extractSpecialCharacters(line);
				if (!specialCharacters.isEmpty()) {
					writer.write(specialCharacters);
					writer.newLine();
				}
			}

			log.info("Special characters extracted and written to output file: " + outputFile);
		} catch (IOException e) {
			log.error("Exception occured while filtering special characters in rtf", e);
		}
	}
	
	/**
	 * Read the Given text file and find out longest word and 
	 * reverse the longest word and write the whole text to another file
	 */
	public void reverseLongestWord(String inputFilePath, String outputFile) {

		// Read the contents of the input file
		String content = Utilities.readFile(inputFilePath);

		// Find the longest word in the content
		String longestWord = Utilities.findLongestWord(content);

		// Reverse the longest word
		String reversedWord = Utilities.reverseWord(longestWord);

		// Write the reversed word to the output file
		Utilities.writeFile(outputFile, reversedWord);
		log.info("Reverse longest word and written to output file: " + outputFile);
	}
}