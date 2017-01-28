package downloader.sequence;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import downloader.exceptions.InvalidSequence;

/**
 * Static class for reading from a file-sequence stream.
 * 
 */
public class FileSequenceReader {
	/**
	 * Returns the data from the next sub-file in the given file sequence
	 * stream.
	 * <p>
	 * If no sub-files remain, returns null. If the stream ends prematurely,
	 * throws an EOFException.
	 * @throws InvalidSequence 
	 */
	public static byte[] readOneFile(InputStream sequence) throws IOException, EOFException, InvalidSequence {

		byte[] fourByte = new byte[4];

		int sizeLength = sequence.read(fourByte, 0, 4);

		// we reached the end of the stream
		if (sizeLength == -1) {
			return null;
		}

		// if size byte != 4 then the sequence is bad
		if (sizeLength != 4) {
			throw new InvalidSequence("Sequence != 4 bytes");
		}
		// get size from the first 4 bytes
		int fileSize = ((0xFF & fourByte[3]) << 24) | ((0xFF & fourByte[2]) << 16) | ((0xFF & fourByte[1]) << 8)
				| (0xFF & fourByte[0]);

		byte[] data = new byte[fileSize];

		int byteVal = 0;
		for (int i = 0; i < data.length; i++) {
			byteVal = sequence.read();
			data[i] = (byte) byteVal;
			if (byteVal == -1) {
				throw new InvalidSequence("Invalid Sequence");
			}
		}

		return data;

	}

}
