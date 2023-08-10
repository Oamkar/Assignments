package com.cts.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Utilities {

	public static String convertFirstCharacter(String line) {
		String sentencePattern = "(?<=\\.\\s)(\\w)";
		Pattern pattern = Pattern.compile(sentencePattern);
		Matcher matcher = pattern.matcher(line);

		StringBuilder modifiedLine = new StringBuilder(line);

		while (matcher.find()) {
			int startIndex = matcher.start();
			char firstChar = Character.toUpperCase(line.charAt(startIndex));

			modifiedLine.setCharAt(startIndex, firstChar);
		}

		return modifiedLine.toString();
	}

	public static String extractSpecialCharacters(String input) {
		String regex = "[^a-zA-Z0-9\\s.!?]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		StringBuilder specialCharacters = new StringBuilder();
		while (matcher.find()) {
			specialCharacters.append(matcher.group());
		}
		return specialCharacters.toString();
	}

	public static String readFile(String filePath) {
		Path path = Paths.get(filePath);

		try {
			byte[] bytes = Files.readAllBytes(path);
			return new String(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String findLongestWord(String content) {
		return Stream.of(content.split("\\W+")).max(Comparator.comparingInt(String::length)).orElse("");
	}

	public static String reverseWord(String word) {
		return new StringBuilder(word).reverse().toString();
	}

	public static void writeFile(String filePath, String content) {
		Path path = Paths.get(filePath);

		try {
			byte[] bytes = content.getBytes();
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
