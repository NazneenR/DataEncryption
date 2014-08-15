package cryptography.encryption;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.String;

public class DataEncryptionActivity extends Activity {

	private final String TAG = "ENCRYPTION_AND_DECRYPTION";

	private Button startProcessButton;
	private DataEncryption encrypter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_encryption_and_decryption);

		Log.d(TAG, "onCreate called");
		startProcessButton = (Button) findViewById(R.id.startButton);
		startProcessButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                encryptAndDecryptString();
            }
        });
    }

	private void encryptAndDecryptString() {
		
	    encrypter = new EncryptionUsingJavaCrypto();

		byte[] encryptedString = encrypter.encryptString("Test String");
		encrypter.decryptString(encryptedString);
	}
}

