/**
 * An exception thrown when a Manifest File is invalid
 */
package downloader.exceptions;
 
 

public class invalidManifestFileException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  public invalidManifestFileException() { super(); }
	  public invalidManifestFileException(String message) { super(message); }
	  public invalidManifestFileException(String message, Throwable cause) { super(message, cause); }
	  public invalidManifestFileException(Throwable cause) { super(cause); }
	}
