/**
 * An exception thrown when a segment URL is not reachable 
 */
package downloader.exceptions;



public class invalidUrlException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  public invalidUrlException() { super(); }
	  public invalidUrlException(String message) { super(message); }
	  public invalidUrlException(String message, Throwable cause) { super(message, cause); }
	  public invalidUrlException(Throwable cause) { super(cause); }
	}
