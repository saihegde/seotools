package com.sailabs.seotools.launchers;

import com.sailabs.seotools.ScriptSearcher;

public class ScriptSearcherLauncher {
	
	public static void main(String[] args) {
		String url = "https://www.directvaloans.com/apply-now";
		ScriptSearcher scriptSearcher = new ScriptSearcher(url);
		System.out.println(scriptSearcher.hasQbitPixelScript());
		System.out.println(scriptSearcher.hasGoogleAnalyticsScript());
		System.out.println(scriptSearcher.hasGoogleTagManagerScript());
		System.out.println(scriptSearcher.hasMarinSoftwareTrackingScript());
	}

}
