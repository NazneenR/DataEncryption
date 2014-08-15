package cryptography.encryption;

import javax.crypto.SecretKey;

public interface DataEncryption {

	public abstract byte[] encryptString(String text, SecretKey key);
	public abstract byte[] decryptString(byte[] text, SecretKey key);
}
