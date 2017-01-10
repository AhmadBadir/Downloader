package downloader.manifest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Segment {
	
	private List<Segment> mirrors;
	protected URL url;
	
	public Segment (String urlStr) throws MalformedURLException{
		url = new URL(urlStr);
		mirrors = new ArrayList<Segment>();
	}
	
	private InputStream getConnectionInputStream (URLConnection yc ) {
		
		try {
			return yc.getInputStream();
		}
		catch (IOException e) {
			return null;
		}
	}
	
	public List <Segment> getMirrors() {
		return mirrors;
	}
	
	public void setMirrors(List <Segment> list) {
		this.mirrors = list;
	}
	
	public void addMirror(Segment segment) {
		this.mirrors.add(segment);
		
	}



}