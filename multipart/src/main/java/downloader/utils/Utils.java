package downloader.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class Utils {
	
	
	public static boolean isUrl (String url) {
		
		try {
			URL httpUrl = new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}
}
