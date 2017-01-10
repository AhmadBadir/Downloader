
package downloader.manifest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ManifestParser {

	private Manifest manifest ;
	private List<Segment>  internalSegs ;
	
	public ManifestParser (Manifest manifest) {
		this.manifest = manifest;
		internalSegs = new ArrayList<Segment> ();
	}
	
	public List<Segment>  getManifestSegment() throws InvalidManifestFormat, IOException {
		
		String[] manifestEntries = this.manifest.getUrlStrings();
		boolean segmentMode=false; 
		for(String manifestLine: manifestEntries) {
			if(manifestLine.trim().equals("")) {
				continue;
			}
			else if (manifestLine.trim().startsWith("http")){
				if(segmentMode == false) {
					segmentMode = true;
					if(!Utils.isvalidURL(manifestLine)) {
						throw  new InvalidManifestFormat("Invalid URL : " + manifestLine) ;
					}
					
					if(isManifestUrl(manifestLine) ) {
						internalSegs.add(new Manifest(manifestLine))	;
					}
					else {
						internalSegs.add(new Segment(manifestLine))	;
					}
				}
				else {
					Segment mirror ;
					if(isManifestUrl(manifestLine) ) {
						mirror = new Manifest(manifestLine);
					}
					else {
						mirror = new Segment(manifestLine);
					}
					internalSegs.get(internalSegs.size()-1).addMirror(mirror);
				}
			}
			else if (manifestLine.trim().startsWith("**")){
				segmentMode = false;
				
			}
			else throw new InvalidManifestFormat("Invalid Manifest Line : " + manifestLine) ;
		}
		return internalSegs;
	}
	
	private boolean isManifestUrl(String manifestLine) throws IOException {
		if((manifestLine.endsWith(".segments"))||("text/segments-manifest".equals(getURLcontentType(manifestLine))) ) {
			return true;
		}
		return false;
	}
	

	private String getURLcontentType(String urlStr) throws IOException {
	    URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection)  url.openConnection();
		connection.setRequestMethod("HEAD");
		connection.connect();
		return connection.getContentType();
	}
	
}