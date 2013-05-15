package com.sailabs.seotools;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.ServiceException;

public class SpreadsheetReader {

	public static void main(String[] args) throws IOException, ServiceException {

		String USERNAME = "saihegde@gmail.com";
		String PASSWORD = "Gxang0rsl";

		SpreadsheetService service = new SpreadsheetService("MySpreadsheetIntegration-v1");
		service.setUserCredentials(USERNAME, PASSWORD);

		URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");

		// Make a request to the API and get all spreadsheets.
		SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
		List<SpreadsheetEntry> spreadsheets = feed.getEntries();

		if (spreadsheets.size() == 0) {
			// TODO: There were no spreadsheets, act accordingly.
		} else {
			System.out.println(spreadsheets.size());
			for(SpreadsheetEntry spreadsheet: spreadsheets){
				System.out.println(spreadsheet.getTitle().getPlainText());
				System.out.println(spreadsheet.getId());
			}
		}

		try {
			// Notice that the url ends with default/public/values.
			// That wasn't obvious from the documentation.
			String urlString = "https://spreadsheets.google.com/feeds/spreadsheets/t0TB-VqJdQ5cZf5wZo7AJ_g";

			// turn the string into a URL
			URL url = new URL(urlString);

			// You could substitute a cell feed here in place of the list feed
			ListFeed listFeed = service.getFeed(url, ListFeed.class);

			for (ListEntry entry : listFeed.getEntries()) {
				CustomElementCollection elements = entry.getCustomElements();
				System.out.println(elements.getValue("Player"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public void read(String gdocUrl, String username, String password){
		
	}

}
