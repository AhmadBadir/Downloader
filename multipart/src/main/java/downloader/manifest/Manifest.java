package downloader.manifest;

import java.io.IOException;

public class Manifest extends UrlLine {
	// Manifest main URL
	private String url;

	public Manifest(String url) throws IOException {
		this.url = url;

	}

	public String getUrl() {
		return url;
	}

}
