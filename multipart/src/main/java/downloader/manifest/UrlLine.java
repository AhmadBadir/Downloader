/**
 * empty abstract class.
 */

package downloader.manifest;

import java.util.ArrayList;

public abstract class UrlLine {

	private ArrayList<String> mirrorAlternatives;

	public ArrayList<String> getMirrorAlternatives() {
		return mirrorAlternatives;
	}

	public void addMirror(String mirrorAlternative) {
		this.getMirrorAlternatives().add(mirrorAlternative);

	}

}
