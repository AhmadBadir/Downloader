package downloader.manifest;

import java.util.ArrayList;

public class Segment extends UrlLine {

	private String mirrorUrl;
	private ArrayList<String> mirrorAlternatives;

	/**
	 * Segment constructor, it takes mirrorURL.
	 * 
	 * @param mirrorUrl, string url for segment.
	 */
	
	public Segment(String mirrorUrl) {
		this.mirrorUrl = mirrorUrl;
		mirrorAlternatives = new ArrayList<String>();
	}

	public void addMirror(String mirrorAlternative) {
		this.mirrorAlternatives.add(mirrorAlternative);

	}

	public String getMirrorUrl() {
		return mirrorUrl;
	}

	public void setMirrorUrl(String mirrorUrl) {
		this.mirrorUrl = mirrorUrl;
	}

}