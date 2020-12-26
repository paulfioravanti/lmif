import java.io.*;

/**
	A class to represent an InputStream that will decrypt data.
	
	@author Paul Fioravanti
	@version 9/5/2005
*/
public class EncrInputStream extends InputStream
{
	private InputStream in;
	private Cipher cipher;
	
	/**
		Constructor that brings in an InputStream from standard Java IO
		carrying ciphertext, and a Cipher to perform the decrypting.
		@param in the InputStream that brings in the ciphertext.
		@param cipher the cipher used to decrypt.
	*/
	public EncrInputStream(InputStream in, Cipher cipher) throws IOException
	{
		this.in = in;
		this.cipher = cipher;
	}
	
	/**
		Compulsory overridden method that simply puts the byte into an array of 1
		and passes it to the read(byte[] b) method.
		@return read(b) a byte in the value range of 0-255 except for -1 at
		end of file
	*/
 	public int read() throws IOException
	{
		int c = in.read();
		byte[] b = {(byte)c};
		return read(b);
	}
	
	/**
		Method to read in and decrypt an array of bytes from the 
		input stream.  The array is instantly 
		passed over to the read(b, off, len) to ensure we have specific start and
		end points for the buffer array.
		@param b the buffer into which the data is read and decrypted
		@return read(b, 0, b.length) the byte array returned from the 
		read(b, off, len) method
	*/
	public int read(byte[] b) throws IOException
	{
		return read(b, 0, b.length);
	}
	
	/**
		Method to read in and decrypt an array of bytes from point off to point len from
		the input stream.
		@param b the buffer into which the data is read and decrypted
		@param off the start offset in array b  at which the data is written.
		@param len the maximum number of bytes to read and decrypt.
		@return read(b, 0, b.length) the byte array returned from the 
		read(b, off, len) method
	*/
	public int read(byte[] b, int off, int len) throws IOException
	{
		System.out.print("read() byte[] b before decryption = "); //debug
		for (int i = 0; i < b.length; i++) //debug
			System.out.print(b[i] + " "); //debug
		System.out.println(); //debug
		
		//read in buffer
		int result = in.read(b, off, len);
		//decrypt buffer array according to chosen cipher.
		cipher.decrypt(b);
		
		System.out.print("read() byte[] b after decryption = "); //debug
		for (int i = 0; i < b.length; i++) //debug
			System.out.print(b[i] + " "); //debug
		System.out.println(); //debug
		
		return result;
	}
}
