package com.sailabs.seotools.launchers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.poi.ss.usermodel.Row;

import au.com.bytecode.opencsv.CSVWriter;

import com.sailabs.seotools.ScriptSearcher;
import com.sailabs.seotools.SpreadsheetReader;

public class ScriptSearcherLauncher {
	
	
	public static void main(String[] args) throws IOException {
		String inputFilePath = "C:/Users/shegde/Downloads/DVAL URLs.xlsx";
		String csvFilePath = "C:/temp/ssoutput.csv";
		if (args.length >= 1){
			inputFilePath = args[0] != null ? args[0] : inputFilePath;
		}
		if (args.length >= 2){
			csvFilePath = args[1] != null ? args[1] : csvFilePath;
		}
		CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFilePath));
		
		List<String[]> data = new ArrayList<String[]>();
		SpreadsheetReader spreadsheetReader = new SpreadsheetReader();
		
		System.out.println("Processing input : " + inputFilePath);
		try{
			try {
				for (Row row : spreadsheetReader.read(inputFilePath)) {
					String url = row.getCell(0).getStringCellValue();
					if (row.getRowNum() == 0){
						data.add(new String[]{url, 
								"Qbit",	
								"Google Analytics",	
								"LinkShare",
								"Google Tag Manager",
								"Google Web Master Tools",
								"Marin Landing Page Tracking Code",
								"Marin Confirmation Page Tracking Code"});
					} else {
						ScriptSearcher scriptSearcher = new ScriptSearcher(url);
						data.add(new String[]{url, 
								String.valueOf(scriptSearcher.hasQbitPixelScript()),	
								String.valueOf(scriptSearcher.hasGoogleAnalyticsScript()),	
								String.valueOf(scriptSearcher.hasLinkshareScript()), 
								String.valueOf(scriptSearcher.hasGoogleTagManagerScript()), 
								String.valueOf(scriptSearcher.hasGoogleWebMasterToolsScript()),
								String.valueOf(scriptSearcher.hasMarinLandingPageTrackingScript()),	
								String.valueOf(scriptSearcher.hasMarinSoftwareTrackingScript())});
					}
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
