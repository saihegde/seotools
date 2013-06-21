package com.sailabs.seotools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class PageSourceReader {
	
	public static String read(String url) {
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
			return webPage.asXml();
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			return "Not a valid URL";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
