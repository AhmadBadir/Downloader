/*
* This is the main manifest class, it reads the URL for the manifest file.
*/

package downloader.manifest;

import java.io.IOException;
import java.io.OutputStream;

import downloader.exceptions.UnreachableMirrorException;
import downloader.exceptions.InvalidManifestFileException;
import downloader.exceptions.InvalidUrlException;

public class Manifest extends UrlLine {
	// Manifest main URL
	private String url;

	/**
	 * Manifest Constructor
	 * @param url
	 * 			Manifest file URL
	 */
	
	public Manifest(String url)  {
		this.url = url;

	}

	public String getUrl() {
		return url;
	}

	@Override
	public void writeContent(OutputStream outputStream) throws IOException, UnreachableMirrorException, InvalidUrlException, InvalidManifestFileException {
		ManifestParser parser = new ManifestParser(this);
		for (UrlLine segment :  parser.getSegments()) {
			segment.writeContent(outputStream);
		}
		
	}

}
