package com.sailabs.seotools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

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
			
			
			System.out.println("Redirect to URL : " + newUrl);
			return newUrl;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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

	public static void main(String[] args) throws IOException {
		//readXLSFile("C:/Users/shegde/Downloads/301redirects.xlsx");
		RedirectDetector rd = new RedirectDetector();
		System.out.println(rd.getRedirectedURL("https://www.directvaloans.com/sfc/militarycom"));
	}

}
