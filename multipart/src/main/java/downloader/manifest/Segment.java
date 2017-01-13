package downloader.manifest;

import java.util.ArrayList;
import java.util.List;

public class Segment {

	private ArrayList<Segment> mirrors;

	public Segment() {
		mirrors = new ArrayList<Segment>();
	}


	public List<Segment> getMirrors() {
		return mirrors;
	}

	public void setMirrors(ArrayList<Segment> mirrors) {
		this.mirrors = mirrors;
	}

	public void addMirror(Segment segment) {
		this.mirrors.add(segment);

	}

}