/**
 * An exception thrown when a segment URL is not reachable 
 */
package downloader.exceptions;
 
 

public class InvalidUrlException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  public InvalidUrlException() { super(); }
	  public InvalidUrlException(String message) { super(message); }
	  public InvalidUrlException(String message, Throwable cause) { super(message, cause); }
	  public InvalidUrlException(Throwable cause) { super(cause); }
	}
