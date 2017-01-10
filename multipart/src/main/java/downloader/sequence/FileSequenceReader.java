package sequence;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Static class for reading from a file-sequence stream.

 */
public class FileSequenceReader {
	/**
	 * Returns the data from the next sub-file in the given file sequence stream.
	 * <p>
	 * If no sub-files remain, returns null. If the stream ends prematurely,
	 * throws an EOFException.
	 */
	public static byte[] readOneFile(InputStream sequence)
		throws IOException, EOFException {
		// sequence files consist of a (4-byte) int giving the size of the sub-file,
		// followed by the sub-file, followed by another size, followed by the sub-file,
		// and so on until EOF
		
	}
}
