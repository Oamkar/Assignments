package com.cts.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Problem1 {
	
	private static final Logger LOGGER = Logger.getLogger(Problem1.class.getName());

	public void convertFirstCharacter() {
		String inputFilePath = "SampleText.rtf";
		String outputFilePath = "SampleTextOutput.rtf";

		try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {

			String line;
			while ((line = br.readLine()) != null) {
				String modifiedLine = Utilities.convertFirstCharacter(line);
				bw.write(modifiedLine);
				bw.newLine();
			}

			LOGGER.info("Conversion successful! Output file: {}" + outputFilePath);
			LOGGER.info("Conversion successful! Output file: "+ outputFilePath);
			System.out.println("Conversion successful! Output file: " + outputFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void filterSpecialCharacters() {
		String inputFilePath = "SampleText.rtf";
		String outputFilePath = "FilterSpecialCharactersOutput.rtf";

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

			String line;
			while ((line = reader.readLine()) != null) {
				String specialCharacters = Utilities.extractSpecialCharacters(line);
				if (!specialCharacters.isEmpty()) {
					writer.write(specialCharacters);
					writer.newLine();
				}
			}

			System.out.println("Special characters extracted and written to output file: " + outputFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reverseLongestWord() {
		// Input and output file paths
		String inputFilePath = "SampleText.rtf";
		String outputFilePath = "ReversedLongestWord.rtf";

		// Read the contents of the input file
		String content = Utilities.readFile(inputFilePath);

		// Find the longest word in the content
		String longestWord = Utilities.findLongestWord(content);

		// Reverse the longest word
		String reversedWord = Utilities.reverseWord(longestWord);

		// Write the reversed word to the output file
		Utilities.writeFile(outputFilePath, reversedWord);
	}
}