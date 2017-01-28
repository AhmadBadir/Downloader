package downloader.multipart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import downloader.exceptions.UnreachableMirrorException;
import downloader.exceptions.InvalidManifestFileException;
import downloader.exceptions.InvalidUrlException;
import downloader.manifest.Manifest;
import downloader.manifest.Segment;
import downloader.manifest.UrlLine;
import downloader.utils.Utils;

/**
 * Static class for reading from a multipart stream.
 */
public class MultiPart {
	/**
	 * Returns an InputStream which streams from the given url.
	 * <p>
	 * If the url ends with the suffix .segments or has the MIME type
	 * text/segments-manifest it is treated as a multipart manifest stream, and
	 * the returned InputStream streams the data represented by the manifest,
	 * not the contents of the manifest itself.
	 * <p>
	 * Otherwise, when the url does not point to a manifest, the returned input
	 * stream streams data directly from the url target.
	 * 
	 * @throws UnreachableMirrorException
	 * @throws InvalidUrlException
	 * @throws InvalidManifestFileException 
	 */
	public static InputStream openStream(String url)
			throws IOException, UnreachableMirrorException, InvalidUrlException, InvalidManifestFileException {

		File outputStream = new File("OutputStream");
		UrlLine mainUrl = null;

		if (Utils.isUrl(url)) {
			if (Utils.isValidManifestUrl(url)) {
				// Manifest
				mainUrl = new Manifest(url);
			} else {
				// it is a segment
				mainUrl = new Segment(url);
			}
			mainUrl.writeContent(new FileOutputStream(outputStream));
			return new FileInputStream(outputStream);
		} else {
			throw new InvalidUrlException("Provided URL is not valid URL");
		}

	}

}
