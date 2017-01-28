package downloader.multiPart;

import downloader.multipart.MultiPart;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

import org.junit.Test;

import downloader.exceptions.UnreachableMirrorException;
import downloader.exceptions.InvalidManifestFileException;
import downloader.exceptions.InvalidUrlException;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MultiPartTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MultiPartTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return (Test) new TestSuite( MultiPartTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    @Test
	public void testOpenStream() throws UnreachableMirrorException, InvalidUrlException, InvalidManifestFileException {
		String url = "http://machine1.birzeit.edu/manifest";
		InputStream inptStream = null;
		try {
			inptStream = MultiPart.openStream(url);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			ByteArrayOutputStream dest = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int readStream = 0;
			while (readStream != -1) {
				dest.write(buffer, 0, readStream);
				readStream = inptStream.read(buffer);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
		}
	}

    
}
