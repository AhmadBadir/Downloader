/**
 * An exception thrown when a Manifest File is invalid
 */
package downloader.exceptions;
 
 

public class InvalidManifestFileException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  public InvalidManifestFileException() { super(); }
	  public InvalidManifestFileException(String message) { super(message); }
	  public InvalidManifestFileException(String message, Throwable cause) { super(message, cause); }
	  public InvalidManifestFileException(Throwable cause) { super(cause); }
	}
