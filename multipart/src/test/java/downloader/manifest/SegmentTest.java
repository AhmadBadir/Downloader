package downloader.manifest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import downloader.exceptions.UnreachableMirrorException;
import downloader.exceptions.invalidManifestFileException;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SegmentTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SegmentTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return (Test) new TestSuite( SegmentTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    @Test
	public final void testWriteContent() throws IOException, UnreachableMirrorException, invalidManifestFileException {
		Segment segment = new Segment("http://machine1.birzeit.edu/manifest");
		File outputFile = new File("outputFile");
		OutputStream outputStream = new FileOutputStream(outputFile) ;
		segment.writeContent(outputStream);
	}
    
    @Test(expected=UnreachableMirrorException.class)
	public final void testUnreachableWriteContent() throws IOException, UnreachableMirrorException, invalidManifestFileException {
		Segment segment = new Segment("http://machine1.birzeit.edu/invalidManifest");
		File outputFile = new File("outputFile");
		OutputStream outputStream = new FileOutputStream(outputFile) ;
		segment.writeContent(outputStream);
	}
}
