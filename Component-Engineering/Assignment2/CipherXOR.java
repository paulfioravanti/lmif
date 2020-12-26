/**
	A class to represent an exclusive OR (XOR) cipher.
	
	@author Paul Fioravanti
	@version 9/5/2005
*/
public class CipherXOR extends Cipher
{
	/**
		Constructor that brings in a key to encrypt and/or decrypt.
		@param key the key used to encrypt/decrypt.
	*/
	public CipherXOR(byte[] key)
	{
		super(key);
	}
	
	/**
		Method to encrypt a file using the key.
		@param data the data to be encrypted.
		@return data the encrypted data.
	*/
	public byte[] encrypt(byte[] data)
	{
		for (int i = 0; i < data.length; i++)
		{
			//ensure array indexes into the range of 0 to 255
			data[i] = (byte)((data[i] ^ key[0]) & 0xff);
		}
		
		return data; 
	}
	
	/**
		Method to decrypt a file using the key.
		@param data the data to be encrypted.
		@return data the encrypted data.
	*/
	public byte[] decrypt(byte[] data)
	{
		//with XOR, encryption is the same as decryption,  
		//so just pass in to encryption method.
		encrypt(data);
		
		return data; 
	}
}
