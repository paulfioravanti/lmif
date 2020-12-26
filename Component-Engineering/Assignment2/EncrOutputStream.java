import java.io.*;

/**
	A class to represent an OutputStream that will encrypt data.
	
	@author Paul Fioravanti
	@version 9/5/2005
*/
public class EncrOutputStream extends OutputStream
{	
	private OutputStream out;
	private Cipher cipher;
	
	/**
		Constructor that brings in an OutputStream from standard Java IO
		carrying ciphertext, and a Cipher to perform the decrypting.
		@param out the OutputStream that brings in the plaintext.
		@param cipher the cipher used to encrypt.
	*/
	public EncrOutputStream(OutputStream out, Cipher cipher)
	{
		this.out = out;
		this.cipher = cipher;
	}
	
	/**
		Compulsory overridden method that simply puts the written byte into an
		array of 1 and passes it to the write(byte[] b) method.
		@param b the byte
	*/
	public void write(int b) throws IOException 
	{
		byte[] c = {(byte)b};
		write(c);
	}
	
	/**
		Method to encrypt and write out an array of bytes to the 
		output stream.  The array is instantly 
		passed over to the write(b, off, len) to ensure we have specific start and
		end points for the buffer array.
		@param b the buffer into which the data is encrypted and written.
	*/
	public void write(byte[] b) throws IOException
	{
		write(b, 0, b.length);
	}	
	
	/**
		Method to encrypt and write out an array of bytes from point off to point
		len to the output stream.
		@param b the buffer into which the data encrypted and written out
		@param off the start offset in that data.
		@param len the number of bytes to encrypt.
	*/
	public void write(byte[] b, int off, int len) throws IOException
	{
		System.out.print("write() byte[] b before encryption = "); //debug
		for (int i = 0; i < b.length; i++) //debug
			System.out.print(b[i] + " "); //debug
		System.out.println(); //debug
		
		//First encrypt b, then pass it into out.
		cipher.encrypt(b);
		
		System.out.print("write() byte[] b after encryption = "); //debug
		for (int i = 0; i < b.length; i++) //debug
			System.out.print(b[i] + " ");  //debug
		System.out.println(); //debug
		
		out.write(b, off, len);
	}
}
