package downloader.manifest;

import org.junit.Test;

import downloader.exceptions.invalidManifestFileException;
import downloader.exceptions.invalidUrlException;

import java.io.IOException;

import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for simple App.
 */
public class ManifestParserTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ManifestParserTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return (Test) new TestSuite( ManifestParserTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    
	/**
	 * @throws IOException 
	 */
	@Test
	public void testManifestParser() throws IOException {
		String urlString = "http://machine1.birzeit.edu/manifest";
		Manifest manifest = new Manifest(urlString );
		new ManifestParser(manifest);
	}
	/**
	 * @throws IOException 
	 */
	@Test(expected=IOException.class)
	public void testInvalidManifestParser() throws IOException {
		String urlString = "invalidURL";
		Manifest emptyManifest = new Manifest(urlString);
		new ManifestParser(emptyManifest);
	}
	
	/**
	 * @throws IOException 
	 * @throws invalidUrlException 
	 * @throws InvalidManifestFormat 
	 */
	@Test(expected=IOException.class)
	public void testGetManifestSegmentIOException() throws IOException, invalidManifestFileException, invalidUrlException {
		String urlString = "http://machine1.birzeit.edu/emptyManifest";
		Manifest emptyManifest = new Manifest(urlString);
		ManifestParser manifestParser = new ManifestParser(emptyManifest);
		manifestParser.getSegments();
	}
	/**
	 * @throws IOException 
	 * @throws invalidUrlException 
	 * @throws InvalidManifestFormat 
	 */
	@Test(expected=invalidManifestFileException.class)
	public void testGetManifestSegmentInvalidStringsSeg() throws IOException, invalidManifestFileException, invalidUrlException {
		String urlString = "http://machine1.birzeit.edu/manifest";
		Manifest emptyManifest = new Manifest(urlString);
		ManifestParser manifestParser = new ManifestParser(emptyManifest);
		manifestParser.getSegments();
	}
}
