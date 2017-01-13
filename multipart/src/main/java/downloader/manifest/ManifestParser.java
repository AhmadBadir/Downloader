
package downloader.manifest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ManifestParser {

	private Manifest manifestFile;
	private ArrayList<Segment> segmentsLinks;

	public ManifestParser(Manifest manifestFile) {
		this.manifestFile = manifestFile;
		segmentsLinks = new ArrayList<Segment>();
	}

	private String getURLcontentType(String manifestUrl) throws IOException {
		URL url = new URL(manifestUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("HEAD");
		connection.connect();
		return connection.getContentType();
	}

	private boolean isValidManifestUrl(String url) throws IOException {
		if (url.endsWith(".segments") || getURLcontentType(url).equals("text/segments-manifest")) {
			return true;
		}
		return false;
	}

}