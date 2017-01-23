package br.ufmg.dcc.nerds.sentimentalanalysis;

import util.Transaction;
import util.TransactionActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lg.database.DatabaseAdapter;
import com.lg.sentimentalanalysis.Method;
import com.lg.sentimentalanalysis.MethodCreator;

/**
 * @author Johnnatan Messias
 */
@SuppressLint("DefaultLocale")
public class MainActivity extends TransactionActivity implements Transaction,
		OnItemSelectedListener {
	private static String TAG = MainActivity.class.getSimpleName();
	private Method method;
	private TextView polarity;
	private Spinner methodsSpinner;
	private EditText inputText;
	private int methodId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		setContentView(R.layout.activity_main);
		polarity = (TextView) findViewById(R.id.polarity);
		polarity.setText("Polarity: 0.0");
		inputText = (EditText) findViewById(R.id.textinput);
		methodsSpinner = (Spinner) findViewById(R.id.methods_spinner);
		methodsSpinner.setOnItemSelectedListener(this);
		inputText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				int pol = 0;
				if(inputText.getText().toString().length() != 0)
				 	pol = method.analyseText(inputText.getText().toString());
				polarity.setText("Polarity: " + String.valueOf(pol));
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		DatabaseAdapter.getInstance().close();
		Log.i(TAG, "App destroyed");
	}

	@Override
	public void execute() throws Exception {
		// Thinking about remove the getAssets from the execute method
		MethodCreator.getInstance().assets = getAssets();
		MethodCreator.context = getApplicationContext();
		method = MethodCreator.getInstance().createMethod(methodId);
	}

	@Override
	public void updateView() {
	}

	public void onClickClear(View view) {
		inputText.setText("");
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(
				parent.getContext(),
				"Method Selected : "
						+ parent.getItemAtPosition(position).toString(),
				Toast.LENGTH_SHORT).show();

		String methodName = parent.getItemAtPosition(position).toString();
		if (methodName.compareTo("Afinn") == 0)
			methodId = 1;
		else if (methodName.compareTo("Emolex") == 0)
			methodId = 2;
		else if (methodName.compareTo("Emoticons") == 0)
			methodId = 3;
		else if (methodName.compareTo("EmoticonsDS") == 0)
			methodId = 4;
		else if (methodName.compareTo("HappinessIndex") == 0)
			methodId = 5;
		else if (methodName.compareTo("MPQA") == 0)
			methodId = 6;
		else if(methodName.compareTo("NRCHashtag") == 0)
			methodId = 7;
		else if (methodName.compareTo("OpinionLexicon") == 0)
			methodId = 8;
		else if (methodName.compareTo("PanasT") == 0)
			methodId = 9;
		else if (methodName.compareTo("Sann") == 0)
			methodId = 10;
		else if (methodName.compareTo("Sasa") == 0)
			methodId = 11;
		else if (methodName.compareTo("SenticNet") == 0)
			methodId = 12;
		else if (methodName.compareTo("SentiStrength") == 0)
			methodId = 14;
		else if (methodName.compareTo("SentiWordNet") == 0)
			methodId = 15;
		else if (methodName.compareTo("SoCal") == 0)
			methodId = 16;
		else if (methodName.compareTo("Stanford") == 0)
			methodId = 17;
		else if (methodName.compareTo("Umigon") == 0)
			methodId = 18;
		else if (methodName.compareTo("Vader") == 0)
			methodId = 19;
		startTransaction(this);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
}
