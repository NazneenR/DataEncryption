package cryptography.encryption;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;

import android.util.Log;

public class EncryptionUsingJavaCrypto implements DataEncryption {
    private String TAG = "EncryptionUsingJavaCrypto";
    private SecretKey mKey;
    private byte[] encrytedString;
    private byte[] textDecrypted;

    public EncryptionUsingJavaCrypto() {
        KeyGenerator kgen;
        mKey = null;
        try {
            kgen = KeyGenerator.getInstance("AES");
            mKey = kgen.generateKey();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] encryptString(String text) {
        try {
            Log.d(TAG, "Crypto: Encrypting string:" + text);
            Cipher encipher = null;
            encipher = Cipher.getInstance("AES");
            encipher.init(Cipher.ENCRYPT_MODE, mKey);
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
    public byte[] decryptString(byte[] stringToBeDecrypted) {
        try {
            Cipher decipher = Cipher.getInstance("AES");
            decipher.init(Cipher.DECRYPT_MODE, mKey);
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
