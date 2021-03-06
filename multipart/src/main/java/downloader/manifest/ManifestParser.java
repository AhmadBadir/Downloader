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

import downloader.exceptions.InvalidManifestFileException;
import downloader.exceptions.InvalidUrlException;
import downloader.utils.Utils;

public class ManifestParser {

	private Manifest manifestFile;
	private ArrayList<UrlLine> segments;

	/**
	 * ManifestParser constructor, it takes Manifest file as an object to
	 * extract the content
	 * 
	 * @param Object
	 *            manifestFile manifestFile object
	 * 
	 */

	public ManifestParser(Manifest manifestFile) {
		this.manifestFile = manifestFile;
		segments = new ArrayList<UrlLine>();
	}


	/**
	 * getSegments function, extracts the segments from manifest file
	 * 
	 * @param
	 * 
	 * @return ArrayList, returns array list of segments and manifest urls
	 *         inside manifest file
	 * 
	 * @throws IOException
	 * @throws InvalidUrlException
	 * @throws InvalidManifestFileException 
	 * 
	 */

	public ArrayList<UrlLine> getSegments() throws IOException, InvalidUrlException, InvalidManifestFileException {
		ArrayList<String> manifestLines = extractManifestLines();
		boolean isAlternative = false;
		for (String manifestline : manifestLines) {
			if (manifestline.trim().equals("**")) {
				isAlternative = false;
			} else if (manifestline.trim().startsWith("http")) {
				// validate if URL is real URL
				if ((Utils.isUrl(manifestline.trim()))) {
					// check if Manifest
					if (Utils.isValidManifestUrl(manifestline.trim())) {
						segments.add(new Manifest(manifestline.trim()));
					} else {
						// it is a Segment
						if (isAlternative) {
						  segments.get(segments.size() - 1).addMirror(manifestline.trim());;
						} else {
							segments.add(new Segment(manifestline.trim()));
							isAlternative = true;
						}
					}
				}
			} else {
				throw new InvalidManifestFileException("Invalided Manifest File");
			}
		}
		return segments;
	}

	/**
	 * extractManifestLines function, extracts the lines from manifest file
	 * 
	 * @param
	 * 
	 * @return ArrayList, returns array list of lines inside the manifest file
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