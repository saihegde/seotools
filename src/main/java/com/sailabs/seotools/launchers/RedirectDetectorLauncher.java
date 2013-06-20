package com.sailabs.seotools.launchers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.poi.ss.usermodel.Row;

import au.com.bytecode.opencsv.CSVWriter;

import com.sailabs.seotools.RedirectDetector;
import com.sailabs.seotools.SpreadsheetReader;

public class RedirectDetectorLauncher {

	public static void main(String[] args) throws IOException {
		String inputFilePath = null;
		String csvFilePath = null;
		if (args.length >= 1){
			inputFilePath = args[0] != null ? args[0] : "C:/Users/shegde/Downloads/301redirects.xlsx";
		}
		if (args.length >= 2){
			csvFilePath = args[1] != null ? args[1] : "C:/temp/rdoutput.csv";
		}
		CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFilePath));
		
		List<String[]> data = new ArrayList<String[]>();
		RedirectDetector rd = new RedirectDetector();
		SpreadsheetReader spreadsheetReader = new SpreadsheetReader();
		System.out.println("Processing input : " + inputFilePath);
		try{
			try {
				for (Row row : spreadsheetReader.read(inputFilePath)) {
					String url = row.getCell(0).getStringCellValue();
					data.add(new String[]{url, rd.getRedirectedURL(url)});
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			csvWriter.writeAll(data);
		} finally {
			csvWriter.close();
			System.out.println("The file has been processed and the output written to : " + csvFilePath);
		}
	}
	
}
