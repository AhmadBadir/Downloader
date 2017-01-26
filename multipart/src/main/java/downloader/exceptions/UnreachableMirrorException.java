/**
 * An exception thrown when a segment URL is not reachable 
 */
package downloader.exceptions;



public class UnreachableMirrorException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  public UnreachableMirrorException() { super(); }
	  public UnreachableMirrorException(String message) { super(message); }
	  public UnreachableMirrorException(String message, Throwable cause) { super(message, cause); }
	  public UnreachableMirrorException(Throwable cause) { super(cause); }
	}
