package com.sailabs.seotools;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MetatagReader {
	
	public static void main(String[] args) throws IOException {
		String url = "https://www.directvaloans.com/apply-now";
		Document document = Jsoup.connect(url).get();
		Elements links = document.select("script"); 
		System.out.println(links);
		Elements noScriptlinks = document.select("noscript"); 
		System.out.println(noScriptlinks);
	}
	
	public static String getMetaTag(String url, String attr) throws IOException{
		Document document = Jsoup.connect(url).get();
		return getMetaTag(document, attr);
	}
	
	public static String getMetaTag(Document document, String attr) {
		Elements elements = document.select("meta[name=" + attr + "]");
		for (Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		elements = document.select("meta[property=" + attr + "]");
		for (Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		return null;
	}

}
