package com.sailabs.seotools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RedirectDetector {

	public String getRedirectedURL(String url) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			System.out.println("Request URL ... " + url);
			conn.connect();
			conn.getInputStream();

			// TODO
			String newUrl = conn.getURL().toURI().toString();

			System.out.println("Redirect to URL : " + newUrl);
			return newUrl;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void readXLSFile(String filePath) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			RedirectDetector rd = new RedirectDetector();
			rd.getRedirectedURL(row.getCell(0).getStringCellValue());
		}
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		// readXLSFile("C:/Users/shegde/Downloads/301redirects.xlsx");
		RedirectDetector rd = new RedirectDetector();
		URL url = new URL("http://www.directvaloans.com/Default.aspx");
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet("http://www.directvaloans.com/Default.aspx");
			System.out.println("executing request " + httpget.getURI());
			// Create a response handler
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
			System.out.println("----------------------------------------");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
			System.out.println("----------------------------------------");
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure immediate deallocation
			// of all system resources
			httpclient.getConnectionManager().shutdown();
		}
	}

	// public static void main(String[] args) {
	// // Create and enter a Context. A Context stores information about the
	// // execution environment of a script.
	// Context cx = Context.enter();
	//
	// try {
	// // Initialize the standard objects (Object, Function, etc.). This
	// // must be done before scripts can be
	// // executed. The null parameter tells initStandardObjects
	// // to create and return a scope object that we use
	// // in later calls.
	// Scriptable scope = cx.initStandardObjects();
	//
	// // Build the script
	// String script =
	// "window.open('http://www.directvaloans.com/Default.aspx')";
	//
	// // Execute the script
	// Object obj = cx.evaluateString(scope, script, "TestScript", 1, null);
	// System.out.println("Object: " + obj);
	//
	// // Cast the result to a string
	// System.out.println((String) obj);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// // Exit the Context. This removes the association between the
	// // Context and the current thread and is an
	// // essential cleanup action. There should be a call to exit for
	// // every call to enter.
	// Context.exit();
	// }
	// }

}
