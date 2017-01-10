package downloader.manifest;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLConnection;
=======
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
>>>>>>> branch 'master' of https://github.com/AhmadBadir/Downloader.git

public class Manifest {
	private String[] urlStrings;
	
	public Manifest(String Urlstring) throws IOException {
		super(Urlstring);
		Urlstring = this.getContent();

	}
	
	public void writeToStream(OutputStream oStream)
			throws IOException, UnreachableSegmentException, InvalidManifestFormat {
		ManifestParser parser = new ManifestParser(this);
		for (Segment segment : parser.getManifestSegment()) {
			segment.writeToStream(oStream);
		}

	}
	
	private String[] getContent() throws IOException {
		URLConnection URLcon = url.openConnection();
		InputStream input = URLcon.getInputStream();
		StringBuilder outStr;
		try {
			BufferedbuffRead buffRead = new BufferedbuffRead(new InputStreambuffRead(input));
			outStr = new StringBuilder();
			String line;
			while ((line = buffRead.readLine()) != null) {
				outStr.append(line + "\r\n");
			}
		} catch (IOException e) {
			throw e;
		} finally {
			in.close();

		}
		return outStr.toString().split("\r\n");
	}

	// Manifest main URL
	private String url;

	
	/**
	 * This function will exctract the content-type from provided url
	 * 
	 *  @param
	 */
	public String getContentType() throws IOException {
		URL url = new URL(this.url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("HEAD");
		conn.connect();
		return conn.getContentType();
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}


}
