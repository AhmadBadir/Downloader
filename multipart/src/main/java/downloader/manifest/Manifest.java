/*
* This is the main manifest class, it reads the URL for the manifest file.
*/

package downloader.manifest;


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

}
