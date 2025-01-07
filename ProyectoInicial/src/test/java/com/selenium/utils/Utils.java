package com.selenium.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;

public class Utils {

	public static void escribir(String linea) {
		Reporter.log(linea);
	}

	public static List<String[]> readCSV(String filePath) throws IOException {
		List<String[]> data = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;

		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (line.isEmpty())
				continue;

			String[] row = line.split(",");
			for (int i = 0; i < row.length; i++) {
				row[i] = row[i].trim();
			}
			data.add(row);
		}
		reader.close();
		return data;
	}

}