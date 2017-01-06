package downloader.manifest;

import java.io.IOException;

public class ManifestParser {

	private Manifest manifestFile;
	
	

	/**
	 * Parser constructor
	 * 
	 * @param Manifest
	 *            Object which contains the segments
	 */
	public ManifestParser(Manifest manifestFile) {
		this.manifestFile = manifestFile;
	}

	/**
	 * @throws IOException 
	 * 
	 */
	private boolean isValidManifestUrl(Manifest manifestFile) throws IOException  {
		if(manifestFile.getUrl().endsWith(".segments") || manifestFile.getContentType().equalsIgnoreCase("text/segments-manifest")) {
			return true;
		}
		return false;
	}

}