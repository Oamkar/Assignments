package com.cts.main;

public class Assessment {

	private static String rftInputFilePath = "inputFiles/SampleText.rtf";
	private final static String OUTPUT_FILE_PATH = "outputFiles/";
	
	public static void main(String[] args) {
		Problem1 problem1 = new Problem1();
		Problem5 problem5 = new Problem5();

		// RTF file execution
		problem1.convertFirstCharacterToUpperCase(rftInputFilePath, OUTPUT_FILE_PATH + "SampleTextOutput.rtf");
		problem1.filterSpecialCharacters(rftInputFilePath, OUTPUT_FILE_PATH + "FilterSpecialCharactersOutput.rtf");
		problem1.reverseLongestWord(rftInputFilePath, OUTPUT_FILE_PATH + "ReversedLongestWord.rtf");

		// API calls
		problem5.execute();
	}
}