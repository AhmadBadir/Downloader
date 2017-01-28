/**
 * An exception thrown when a File Sequence is invalid
 */
package downloader.exceptions;
 
 

public class InvalidSequence extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  public InvalidSequence() { super(); }
	  public InvalidSequence(String message) { super(message); }
	  public InvalidSequence(String message, Throwable cause) { super(message, cause); }
	  public InvalidSequence(Throwable cause) { super(cause); }
	}
