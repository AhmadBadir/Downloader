
package downloader.manifest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import downloader.utils.Utils;

public class ManifestParser {

	private Manifest manifestFile;
	private ArrayList<UrlLine> segments;

	public ManifestParser(Manifest manifestFile) {
		this.manifestFile = manifestFile;
		segments = new ArrayList<UrlLine>();
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

	public ArrayList<UrlLine> getSegments() throws IOException {

		ArrayList<String> manifestLines = extractManifestLines();
		boolean isAlternative = false;
		for(String manifestline :  manifestLines) {
			if(manifestline.trim().equals("**")) {
				isAlternative = false;
			} else if (manifestline.trim().startsWith("http")) {
				//validate if URL is real URL
				if((Utils.isUrl(manifestline.trim()))) {
					//check if Manifest
					if(isValidManifestUrl(manifestline.trim())) {
						segments.add(new Manifest(manifestline.trim()));
					
					} else { 
						// it is a Segment
						if(isAlternative) {
							Segment s = (Segment) segments.get(segments.size() - 1);
							s.addMirror(manifestline);
						} else {
							segments.add(new Segment(manifestline.trim()));
							isAlternative = true;
						}
					}
				}
			}
		}
		return segments;
	}

	private ArrayList<String> extractManifestLines() throws IOException {
		ArrayList<String> manifestLines = new ArrayList<String>();
		URLConnection manifestUrlCon = new URL(this.manifestFile.getUrl()).openConnection();

		InputStream in = manifestUrlCon.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String manifestLine = null;

		while ((manifestLine = reader.readLine()) != null) {
			manifestLines.add(manifestLine);
		}
		in.close();
		return manifestLines;
	}

}