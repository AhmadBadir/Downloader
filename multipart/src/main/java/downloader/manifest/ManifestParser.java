
package downloader.manifest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ManifestParser {


	public ManifestParser () {

	}

	private String getURLcontentType(String urlStr) throws IOException {
	    URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection)  url.openConnection();
		connection.setRequestMethod("HEAD");
		connection.connect();
		return connection.getContentType();
	}
	
}