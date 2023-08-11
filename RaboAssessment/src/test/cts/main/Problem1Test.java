package test.cts.main;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.cts.main.Problem1;

public class Problem1Test {
	
	private Problem1 problem1 = new Problem1();
	private static final String UPPER_CASE_TEST_EXPECTED_OUTPUT = "a text file is something you could type up in a word processor. A text file is made of a sequence of characters, organized as a series of horizontal lines of text";

	@Test
	public void convertFirstCharacterToUpperCaseTest() throws IOException {
		
		// Initialize
		String outputFilePath = "testFiles/output/SampleTextOutput.rtf";
		StringBuilder outputText = new StringBuilder();

		// Act
		problem1.convertFirstCharacterToUpperCase("testFiles/input/SampleText.rtf", outputFilePath);

		BufferedReader br = new BufferedReader(new FileReader(outputFilePath));
		String line;
		while ((line = br.readLine()) != null) {
			outputText.append(line);
		}
		
		// Assert
		assertTrue(!outputText.toString().isEmpty());
		assertTrue(outputText.toString().contains(UPPER_CASE_TEST_EXPECTED_OUTPUT));
	}
}
