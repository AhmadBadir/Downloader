package downloader.manifest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import downloader.exceptions.UnreachableMirrorException;

public class Segment extends UrlLine {

	private String mirrorUrl;

	/**
	 * Segment constructor, it takes mirrorURL.
	 * 
	 * @param mirrorUrl,
	 *            string url for segment.
	 */

	public Segment(String mirrorUrl) {
		this.mirrorUrl = mirrorUrl;
	}

	public void addMirror(String mirrorAlternative) {
		this.getMirrorAlternatives().add(mirrorAlternative);

	}

	/**
	 * write segment content into an output stream
	 * 
	 * @param outputStream
	 *            outputStream is Output stream to write segment content into
	 * @throws IOException
	 *             Exception while downloading content
	 * @throws UnreachableMirrorException
	 *             Exception thrown in case the segment URL is not reachable
	 */
	public void writeContent(OutputStream outputStream) throws IOException, UnreachableMirrorException {
		URL mirror = new URL(this.mirrorUrl);
		boolean foundWorkingMirror = false;

		URLConnection conn = mirror.openConnection();

		InputStream in = conn.getInputStream();

		if (in == null) {
			// go for alternative methods
			for (int i = 0; i < this.getMirrorAlternatives().size(); i++) {
				mirror = new URL(this.getMirrorAlternatives().get(i));
				conn = mirror.openConnection();
				in = conn.getInputStream();
				if (in != null) {
					// found alternative
					foundWorkingMirror = true;
					break;
				}
			}
			if (!foundWorkingMirror) {
				// we didn't find any mirror working
				throw new UnreachableMirrorException("No mirrors found");
			}
		} else {
			foundWorkingMirror = true;
		}
		if (in != null && foundWorkingMirror) {
			try {
				int inputChar;
				while ((inputChar = in.read()) != -1) {
					outputStream.write(inputChar);
				}
			} catch (IOException e) {
				throw e;
			} finally {
				in.close();
			}
		}
	}

	public String getMirrorUrl() {
		return mirrorUrl;
	}

	public void setMirrorUrl(String mirrorUrl) {
		this.mirrorUrl = mirrorUrl;
	}

}