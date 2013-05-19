package com.sailabs.seotools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RedirectDetector {

	public String getRedirectedURL(String url) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpParams params = client.getParams();
			HttpClientParams.setRedirecting(params, false);
			HttpGet method = new HttpGet(url);
			HttpResponse resp;
			resp = client.execute(method);
			String location = resp.getLastHeader("Location") == null ? null : resp.getLastHeader("Location").getValue();
			return location;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Row> readXLSFile(String filePath) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.getSheetAt(0);
		// XSSFRow row;
		// XSSFCell cell;
		Iterator<Row> iterator = sheet.rowIterator();
		List<Row> rows = new ArrayList<Row>();
		while (iterator.hasNext())
			rows.add((Row) iterator.next());

		return rows;
	}

	public static void main(String[] args) {
		RedirectDetector rd = new RedirectDetector();
		try {
			for (Row row : rd.readXLSFile("C:/Users/Sai/Downloads/301redirects.xlsx")) {
				System.out.println("Original URL: " + row.getCell(0).getStringCellValue());
				System.out.println("Redirected URL: " + rd.getRedirectedURL(row.getCell(0).getStringCellValue()));
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
