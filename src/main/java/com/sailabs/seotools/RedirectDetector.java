package com.sailabs.seotools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class RedirectDetector {
	
	public List<Row> readXLSFile(String filePath) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.getSheetAt(0);
		Iterator<Row> iterator = sheet.rowIterator();
		List<Row> rows = new ArrayList<Row>();
		while (iterator.hasNext())
			rows.add((Row) iterator.next());
		return rows;
	}

	@SuppressWarnings("deprecation")
	public String getRedirectedURL(String url)  {
		WebClient webClient = new WebClient();
		webClient.setJavaScriptEnabled(false);
		webClient.setThrowExceptionOnScriptError(false);
		webClient.setCssEnabled(false);
		try {
			webClient.setUseInsecureSSL(true);
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
		}
		webClient.setRedirectEnabled(true);
		HtmlPage webPage;
		try {
			webPage = webClient.getPage(new URL(url));
			return webPage.getUrl().toString();
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	public static void main(String[] args) {
		RedirectDetector rd = new RedirectDetector();
		try {
			for (Row row : rd.readXLSFile("C:/Users/shegde/Downloads/301redirects.xlsx")) {
				System.out.println("Original URL: " + row.getCell(0).getStringCellValue());
				System.out.println("Redirected URL: " + rd.getRedirectedURL(row.getCell(0).getStringCellValue()));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
