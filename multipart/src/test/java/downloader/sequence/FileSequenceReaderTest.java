package downloader.sequence;

import downloader.multipart.MultiPart;
import static org.junit.Assert.assertEquals;

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
public class FileSequenceReaderTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FileSequenceReaderTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return (Test) new TestSuite( FileSequenceReaderTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
	@Test
	public void testReadOneFile() throws UnreachableMirrorException, InvalidUrlException, InvalidManifestFileException {
		String url = "http://machine1.birzeit.edu/manifest";
		InputStream inpustStream = null;
		try {
			inpustStream = MultiPart.openStream(url);
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			byte[] data = FileSequenceReader.readOneFile(inpustStream);
			assertEquals(17774, data.length);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
			assertEquals(null, e);
		}
		assertEquals(null, null);
	}

    
}
