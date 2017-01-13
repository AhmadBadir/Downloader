package downloader.manifest;

import java.util.ArrayList;
import java.util.List;

public class Segment {

	private List<Segment> mirrors;

	public Segment() {
		mirrors = new ArrayList<Segment>();
	}


	public List<Segment> getMirrors() {
		return mirrors;
	}

	public void setMirrors(List<Segment> mirrors) {
		this.mirrors = mirrors;
	}

	public void addMirror(Segment segment) {
		this.mirrors.add(segment);

	}

}