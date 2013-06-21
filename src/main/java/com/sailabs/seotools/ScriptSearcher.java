package com.sailabs.seotools;


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
	
	private String pageSource;
	
	public ScriptSearcher(String url){
		this.pageSource = PageSourceReader.read(url);
	}
	
	public boolean hasQbitPixelScript(){
		return pageSource.contains("//d3c3cq33003psk.cloudfront.net/opentag-55742-246999.js");
	}
	
	public boolean hasGoogleAnalyticsScript(){
		return pageSource.contains("google-analytics.com");
	}
	
	public boolean hasOptamizlyScript(){
		return pageSource.contains("optamizly.com");
	}
	
	public boolean hasGoogleConversionOptimizerScript(){
		return pageSource.contains("optamizly.com");
	}
	
	public boolean hasLinkshareScript(){
		return pageSource.contains("linkshare.com");
	}
	
	public boolean hasGoogleTagManagerScript(){
		return pageSource.contains("www.googletagmanager.com/ns.html");
	}

	public boolean hasGoogleWebMasterToolsScript(){
		return pageSource.contains("google-site-verification");
	}
	
	public boolean hasMarinSoftwareTrackingScript(){
		return pageSource.contains("tracker.marinsm.com");
	}
	
	public boolean hasMarinLandingPageTrackingScript(){
		return pageSource.contains("https://tracker.marinsm.com/tp?act=1&amp;cid=w0ynh6crt0&amp;script=no");
	}
	
}
