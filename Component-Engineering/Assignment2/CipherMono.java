/**
	A class to represent a monoalphabetic substitution cipher,
	
	@author Paul Fioravanti
	@version 9/5/2005
*/
public class CipherMono extends Cipher
{
	//reverseKey, which is derived from the key.
	private byte[] reverseKey;
	
	/**
		Constructor that brings in a key to encrypt and also
		derives a reverseKey from the key for decryption.
		@param key the key used to encrypt.
	*/
	public CipherMono(byte[] key)
	{
		super(key);
		
		reverseKey = new byte[key.length];
		
		for (int i = 0; i < reverseKey.length; i++)
		{
			//ensure array indexes into the range of 0 to 255
			reverseKey[key[i] & 0xff] = (byte)i;
		}
	}
	
	/**
		Method to encrypt a file using the key.
		@param data the data to be encrypted.
		@return data the encrypted data.
	*/
	public byte[] encrypt(byte[] data)
	{
		System.out.print("key = "); //debug
		for (int i = 0; i < key.length; i++) //debug
			System.out.print(key[i] + " "); //debug
		System.out.println(); //debug
		
		System.out.print("reverseKey = "); //debug
		for (int i = 0; i < reverseKey.length; i++) //debug
			System.out.print(reverseKey[i] + " "); //debug
		System.out.println(); //debug
	
		for (int i = 0; i < data.length; i++)
		{
			//ensure array indexes into the range of 0 to 255
			data[i] = (byte)(key[data[i] & 0xff]);
		}

		return data;
	}
	
	/**
		Method to decrypt a file using the reverseKey.
		@param data the data to be decrypted.
		@return data the decrypted data.
	*/
	public byte[] decrypt(byte[] data)
	{
		System.out.print("key = "); //debug
		for (int i = 0; i < key.length; i++) //debug
			System.out.print(key[i] + " "); //debug
		System.out.println(); //debug
			
		System.out.print("reverseKey = "); //debug
		for (int i = 0; i < reverseKey.length; i++) //debug
			System.out.print(reverseKey[i] + " "); //debug
		System.out.println(); //debug
	
		for (int i = 0; i < data.length; i++)
		{
			//ensure array indexes into the range of 0 to 255
			data[i] = (byte)(reverseKey[data[i] & 0xff]);
		}
		
		return data;
	}
}
