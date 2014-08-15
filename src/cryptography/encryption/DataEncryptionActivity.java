package cryptography.encryption;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.lang.String;

public class DataEncryptionActivity extends Activity {

	private final String TAG = "ENCRYPTION_AND_DECRYPTION";

	private Button startProcessButton;
	private DataEncryption encrypter;
    private TextView textBox;
    private TextView textBoxForDecryptedValue;

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_encryption_and_decryption);

		Log.d(TAG, "onCreate called");
		startProcessButton = (Button) findViewById(R.id.startButton);
		textBox = (TextView) findViewById(R.id.editText4);
		textBoxForDecryptedValue = (TextView) findViewById(R.id.editText6);

		startProcessButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    encryptAndDecryptString(textBox,textBoxForDecryptedValue);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

	private void encryptAndDecryptString(TextView textBox, TextView textBoxForDecryptedValue) throws UnsupportedEncodingException {
		
	    encrypter = new EncryptionUsingJavaCrypto();

		byte[] encryptedString = encrypter.encryptString("Test String");
        textBox.setText(new String(encryptedString, "UTF-8"));

        byte[] decrypted_bytes = encrypter.decryptString(encryptedString);
        textBoxForDecryptedValue.setText(new String(decrypted_bytes, "UTF-8"));
	}
}

