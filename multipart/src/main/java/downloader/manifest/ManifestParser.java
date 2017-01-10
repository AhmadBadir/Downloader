
package downloader.manifest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ManifestParser {

<<<<<<< HEAD
	private Manifest manifest ;
	private List<Segment>  internalSegs ;
=======
	private Manifest manifestFile;
>>>>>>> branch 'master' of https://github.com/AhmadBadir/Downloader.git
	
<<<<<<< HEAD
	public ManifestParser (Manifest manifest) {
		this.manifest = manifest;
		internalSegs = new ArrayList<Segment> ();
=======
	

	/**
	 * Parser constructor
	 * 
	 * @param Manifest
	 *            Object which contains the segments
	 */
	public ManifestParser(Manifest manifestFile) {
		this.manifestFile = manifestFile;
>>>>>>> branch 'master' of https://github.com/AhmadBadir/Downloader.git
	}
<<<<<<< HEAD
	
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
=======

	/**
	 * @throws IOException 
	 * 
	 */
	private boolean isValidManifestUrl(Manifest manifestFile) throws IOException  {
		if(manifestFile.getUrl().endsWith(".segments") || manifestFile.getContentType().equalsIgnoreCase("text/segments-manifest")) {
>>>>>>> branch 'master' of https://github.com/AhmadBadir/Downloader.git
			return true;
		}
		return false;
	}
<<<<<<< HEAD
	

	private String getURLcontentType(String urlStr) throws IOException {
	    URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection)  url.openConnection();
		connection.setRequestMethod("HEAD");
		connection.connect();
		return connection.getContentType();
	}
	
=======

>>>>>>> branch 'master' of https://github.com/AhmadBadir/Downloader.git
}