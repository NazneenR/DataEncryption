package cryptography.encryption;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.widget.TextView;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class DataEncryptionService {

    private final Context context;
    private DataEncryption encrypter;
    private SecretKey key;

    public DataEncryptionService(Context applicationContext) {
       this.context = applicationContext;
       encrypter = new EncryptionUsingJavaCrypto();
       generateSecretKey();
       storeSecretKey();
    }

    public void encryptAndDecryptString(TextView textBox, TextView textBoxForDecryptedValue) throws UnsupportedEncodingException {

        SecretKey originalKey = reclaimSecretKey();
        byte[] encryptedString = encrypter.encryptString("Test String", originalKey);
        System.out.println("encrypted key ^^^^^^"+ encryptedString);
        textBox.setText(new String(encryptedString, "UTF-8"));

        byte[] decrypted_bytes = encrypter.decryptString(encryptedString, originalKey);
        textBoxForDecryptedValue.setText(new String(decrypted_bytes, "UTF-8"));
    }

    private SecretKey reclaimSecretKey() {
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE);
        System.out.println("in reclaim**********");
        String keyFromSharedPref = sharedPref.getString((Constants.KEY), "String");
        System.out.println("&&&&&&&& -->>" + keyFromSharedPref);
        byte[] encodedKey     = Base64.decode(keyFromSharedPref, Base64.DEFAULT);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "DES");
    }

    private void generateSecretKey() {
        System.out.println("In here -> generate");
        KeyGenerator kgen;
        key = null;
        try {
            kgen = KeyGenerator.getInstance("AES");
            key = kgen.generateKey();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void storeSecretKey() {
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.KEY, Base64.encodeToString(key.getEncoded(), Base64.DEFAULT));
        editor.commit();
    }
}
