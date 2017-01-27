/**
 * empty abstract class.
 */

package downloader.manifest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import downloader.exceptions.UnreachableMirrorException;
import downloader.exceptions.invalidManifestFileException;
import downloader.exceptions.invalidUrlException;

public abstract class UrlLine {

	private ArrayList<String> mirrorAlternatives;

	public ArrayList<String> getMirrorAlternatives() {
		return mirrorAlternatives;
	}

	public void addMirror(String mirrorAlternative) {
		this.getMirrorAlternatives().add(mirrorAlternative);

	}
	
	public abstract void writeContent(OutputStream outputStream) throws IOException, UnreachableMirrorException, invalidUrlException, invalidManifestFileException;


}
