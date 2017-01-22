package downloader.manifest;


public class Manifest extends UrlLine {
	// Manifest main URL
	private String url;


	public Manifest(String url)  {
		this.url = url;

	}

	public String getUrl() {
		return url;
	}

}
