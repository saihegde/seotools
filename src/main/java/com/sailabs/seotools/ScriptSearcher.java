package com.sailabs.seotools;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ScriptSearcher {
	
	/*Qbit
	Google Analytics
	Optamizly
	Google Conversion Optimizer
	Linkshare
	Google Tag Manager
	Google Web Master Tools
	Marin - landing page tracking code
	Marin - confirmation page tracking code*/
	
	private Document document;
	
	public ScriptSearcher(String url){
		try {
			this.document = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean hasQbitPixelScript(){
		List<Element> scriptElements = getScripts(document);
		for(Element element : scriptElements){
			if(element.attr("src").equals("//d3c3cq33003psk.cloudfront.net/opentag-55742-246999.js")){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasGoogleAnalyticsScript(){
		List<Element> scriptElements = getScripts(document);
		for(Element element : scriptElements){
			if(element.toString().contains("google-analytics.com")){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasOptamizlyScript(){
		return false;
	}
	
	public boolean hasGoogleConevrsionOptimizerScript(){
		return false;
	}
	
	public boolean hasLinkshareScript(){
		return false;
	}
	
	public boolean hasGoogleTagManagerScript(){
		List<Element> scriptElements = getScripts(document);
		System.out.println(getScripts(document));
		for(Element element : scriptElements){
			if(element.toString().contains("*********************")){
				return true;
			}
		}
		return false;
	}

	public boolean hasGoogleWebMasterToolsScript(){
		return false;
	}
	
	public boolean hasMarinSoftwareTrackingScript(){
		List<Element> scriptElements = getNoScripts(document);
		for(Element element : scriptElements){
			if(element.toString().contains("tracker.marinsm.com")){
				return true;
			}
		}
		return false;
	}
	
	private List<Element> getScripts(Document doc) {
		return document.select("script");
	}
	
	private List<Element> getNoScripts(Document doc) {
		return document.select("noscript");
	}
}
