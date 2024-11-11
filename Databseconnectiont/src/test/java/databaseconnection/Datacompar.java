package databaseconnection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Datacompar {
	public static void main(String[] args) {

		try {
			// File paths for the files to compare
			String filePath1 = "D:\\data bask\\Compar\\firstFile.txt";
			String filePath2 = "D:\\data bask\\Compar\\secondFile.txt";

			// Perform comparison and get the results
			List<String> matchedRecords = new ArrayList<>();
			List<String> unmatchedRecords = new ArrayList<>();
			compareFiles(filePath1, filePath2, matchedRecords, unmatchedRecords);

			// Display matched and unmatched records
			System.out.println("Matched Records:");
			matchedRecords.forEach(System.out::println);

			System.out.println("Unmatched Records:");
			unmatchedRecords.forEach(System.out::println);

		} finally {
		}
	}

	// Method to compare two text files and store matched/unmatched lines
	private static void compareFiles(String file1, String file2, List<String> matchedRecords,
			List<String> unmatchedRecords) {
		try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
				BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {

			String line1, line2;
			int lineNumber = 1;

			// Compare the files line by line
			while ((line1 = reader1.readLine()) != null) {
				line2 = reader2.readLine();
				if (line2 == null || !line1.equals(line2)) {
					unmatchedRecords.add("Line " + lineNumber + ": " + line1 + " | "
							+ (line2 != null ? line2 : "No match in file 2"));
				} else {
					matchedRecords.add("Line " + lineNumber + ": " + line1);
				}
				lineNumber++;
			}

			// Check for extra lines in file2
			while ((line2 = reader2.readLine()) != null) {
				unmatchedRecords.add("Line " + lineNumber + ": No match in file 1 | " + line2);
				lineNumber++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
