import java.io.*;

/** Student test program for Component Engineering 2005 Asst 2
 *
 *	Usage: java Crypt {-e|-d} {-X|-M} keyfile inputfile outputfile
 *	where -e means encrypt, -d means decrypt, -X means use the XOR cipher,
 *	-M the Mono cipher, and keyfile contains the key for the cipher (1 byte
 *	for -X, 256 bytes for -M).
 *
 *
 *	@author Chris Steketee
 *	@version 29/03/2005
 */

public class Crypt  {

	public static void main( String[] args ) throws IOException  {
		Cipher cipher = null;
		byte[] key = null;
		InputStream in = null;
		OutputStream out = null;
		InputStream keyFile = null;

		try  {
			if ( args.length != 5 )
				throw new IllegalArgumentException( "Not 5 arguments" );
			if ( !( args[0].equals( "-e" ) ) && !( args[0].equals( "-d" ) ) )
				throw new IllegalArgumentException( " First argument must be -e or -d" );
			if ( !( args[1].equals( "-X" ) ) && !( args[1].equals( "-M" ) ) )
				throw new IllegalArgumentException( " Second argument must be -X or -M" );

			// Read the key:
			keyFile = new FileInputStream( args[2] );
			int keyLen = keyFile.available();
			key = new byte[ keyLen ];
			keyFile.read( key );

			// Use Strategy to create the encryption / decryption object:
			if ( args[1].equals( "-X" ) )
				cipher = new CipherXOR( key );
			else
				cipher = new CipherMono( key );

			in = new FileInputStream( args[3] );
			out = new FileOutputStream( args[4] );

			// Use Decorator to either encrypt the output or decrypt the input:

			if ( args[0].equals( "-e" ) )
				out = new EncrOutputStream( out, cipher );
			else
				in = new EncrInputStream( in, cipher );
		}
		catch ( Exception ex ) {
			ex.printStackTrace( System.err );
			System.err.println( "Usage: java Crypt {-e|-d} {-X|-M} keyfile inputfile outputfile" );
			System.exit( 1 );
		}


		// Read the input one buffer at a time and write to the output:
		byte[] buffer = new byte[1024];
		int len;
		while ( ( len = in.read( buffer ) ) >= 0 )
			out.write( buffer, 0, len );
		in.close();
		out.close();
	}
}
