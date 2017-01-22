/*
 * class which contains helper functions
 */

package downloader.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class Utils {
	
	/**
	 * boolean function which validates if a url is real or fake url
	 * @param url, string url
	 * @return true if url is real, false otherwise
	 */
	
	public static boolean isUrl (String url) {
		
		try {
			URL httpUrl = new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}
}
