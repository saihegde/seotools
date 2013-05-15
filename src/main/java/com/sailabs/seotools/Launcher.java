package com.sailabs.seotools;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Launcher {

	public static void main(String[] args) throws IOException {
		Document document = Jsoup.connect("https://www.directvaloans.com").get();
		String title = MetatagReader.getMetaTag(document, "title");
		String description = MetatagReader.getMetaTag(document, "description");
		System.out.println("title: " + title);
		System.out.println("description: " + description);
		// and others you need to
		//String ogImage = getMetaTag(document, "og:image") 
	}
	
}
