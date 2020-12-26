/**
	This abstract class is the superclass of all classes representing a cipher.
	
	Applications that need to define a subclass of Cipher must always provide a 
	method that returns an encrypted byte array, and a method that returns a 
	decrypted byte array. 

	@author Paul Fioravanti
	@version 9/5/2005
*/
public abstract class Cipher
{
	protected byte[] key;
	
	/**
		Constructor that brings in a key to encrypt and/or decrypt
		@param key the key used to encrypt and/or decrypt
	*/
	public Cipher(byte[] key)
	{
		this.key = key;
	}
	
	/**
		Abstract encrypt method that must be implemented in subclasses
		@param file the file to be encrypted
	*/
	abstract public byte[] encrypt(byte[] file);
	
	/**
		Abstract decrypt method that must be implemented in subclasses
		@param file the file to be decrypted
	*/
	abstract public byte[] decrypt(byte[] file);
}
