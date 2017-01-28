/*
 * class which contains helper functions
 */

package downloader.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import downloader.exceptions.InvalidUrlException;

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
	/**
	 * getURLcontentType function, it takes Manifest url and extracts the
	 * content type
	 * 
	 * @param String
	 *            manifestUrl manifest string url
	 * 
	 * @return string content type
	 * 
	 * @throws IOException
	 * @throws InvalidUrlException
	 * 
	 */

	public static String getURLcontentType(String manifestUrl) throws IOException, InvalidUrlException {
		if (Utils.isUrl(manifestUrl)) {
			URL url = new URL(manifestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			return connection.getContentType();
		} else {
			throw new InvalidUrlException("The URL provided is not valid URL");
		}
	}
	
	/**
	 * isValidManifestUrl function, check if the manifest url is valid
	 * 
	 * @param String
	 *            url, manifest string url
	 * 
	 * @return boolean true if url is valid manifest Url, false otherwise
	 * 
	 * @throws IOException
	 * @throws InvalidUrlException
	 * 
	 */

	public static boolean isValidManifestUrl(String url) throws IOException, InvalidUrlException {
		if (url.endsWith(".segments") || Utils.getURLcontentType(url).equals("text/segments-manifest")) {
			return true;
		}
		return false;
	}
}
