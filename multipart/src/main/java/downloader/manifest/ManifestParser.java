/**
 * Main class for manifestParsing, it takes the URL, parse its content.
 */

package downloader.manifest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import downloader.exceptions.invalidUrlException;
import downloader.utils.Utils;

public class ManifestParser {

	private Manifest manifestFile;
	private ArrayList<UrlLine> segments;

	/**
	 * ManifestParser constructor, it takes Manifest file as an object to extract
	 * the content
	 * 
	 * @param Object manifestFile manifestFile object
	 * 
	 */
	
	public ManifestParser(Manifest manifestFile) {
		this.manifestFile = manifestFile;
		segments = new ArrayList<UrlLine>();
	}

	/**
	 * getURLcontentType function, it takes Manifest url and extracts the
	 * content type
	 * 
	 * @param String manifestUrl manifest string url
	 * 
	 * @return string content type
	 * 
	 * @throws IOException
	 * @throws invalidUrlException 
	 * 
	 */
	
	private String getURLcontentType(String manifestUrl) throws IOException, invalidUrlException {
		if(Utils.isUrl(manifestUrl)) {
		URL url = new URL(manifestUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("HEAD");
		connection.connect();
		return connection.getContentType();
		} else {
			throw new invalidUrlException("The URL provided is not valid URL");
		}
	}

	/**
	 * isValidManifestUrl function, check if the manifest url is valid
	 * 
	 * @param String url, manifest string url
	 * 
	 * @return boolean true if url is valid manifest Url, false otherwise
	 * 
	 * @throws IOException
	 * @throws invalidUrlException 
	 * 
	 */
	
	private boolean isValidManifestUrl(String url) throws IOException, invalidUrlException {
		if (url.endsWith(".segments") || getURLcontentType(url).equals("text/segments-manifest")) {
			return true;
		}
		return false;
	}

	/**
	 * getSegments function, extracts the segments from manifest file
	 * 
	 * @param
	 * 
	 * @return ArrayList, returns array list of segments and manifest urls
	 * inside manifest file
	 * 
	 * @throws IOException
	 * @throws invalidUrlException 
	 * 
	 */
	
	public ArrayList<UrlLine> getSegments() throws IOException, invalidUrlException {
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

	/**
	 * extractManifestLines function, extracts the lines from manifest file
	 * 
	 * @param
	 * 
	 * @return ArrayList, returns array list of lines inside the manifest
	 * file
	 * 
	 * @throws IOException
	 * 
	 */
	
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