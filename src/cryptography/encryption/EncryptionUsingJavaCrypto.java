package cryptography.encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;

import android.util.Log;

public class EncryptionUsingJavaCrypto implements DataEncryption {
    private String TAG = "EncryptionUsingJavaCrypto";
    private byte[] encrytedString;
    private byte[] textDecrypted;

    @Override
    public byte[] encryptString(String text, SecretKey key) {
        try {
            Log.d(TAG, "Crypto: Encrypting string:" + text);
            Cipher encipher = null;
            encipher = Cipher.getInstance("AES");
            encipher.init(Cipher.ENCRYPT_MODE, key);
            encrytedString = encipher.doFinal(text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return encrytedString;

    }

    @Override
    public byte[] decryptString(byte[] stringToBeDecrypted, SecretKey key) {
        try {
            Cipher decipher = Cipher.getInstance("AES");
            decipher.init(Cipher.DECRYPT_MODE, key);
            textDecrypted = decipher.doFinal(stringToBeDecrypted);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return textDecrypted;
    }
}
