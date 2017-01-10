package downloader.manifest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Manifest {
	private String[] urlStrings;
	
	public Manifest(String Urlstring) throws IOException {

	}
	
	// Manifest main URL
	private String url;

	
	/**
	 * This function will get the content type from url
	 * 
	 *  
	 */
	public String getContentType() throws IOException {
		URL url = new URL(this.url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("HEAD");
		conn.connect();
		return conn.getContentType();
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}


}
