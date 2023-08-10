package com.cts.main;

public class Assessment {

	private static String rftInputFilePath = "inputFiles/SampleText.rtf";
	
	public static void main(String[] args) {
		
		// RTF file execution
		new Problem1().convertFirstCharacterToUpperCase(rftInputFilePath, "SampleTextOutput.rtf");
		new Problem1().filterSpecialCharacters(rftInputFilePath, "FilterSpecialCharactersOutput.rtf");
		new Problem1().reverseLongestWord(rftInputFilePath, "ReversedLongestWord.rtf");
		
		// API calls
		new Problem5().execute();
	}
}