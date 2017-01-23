package com.lg.sentimentalanalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.util.Log;

public abstract class Method {

	private static String TAG = Method.class.getSimpleName();
	protected final int POSITIVE = 1;
	protected final int NEGATIVE = -1;
	protected final int NEUTRAL = 0;

	public abstract void loadDictionaries();

	public abstract int analyseText(String text);

	public void analyseFile(String filePath) {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					MethodCreator.assets.open(filePath)));
			String line = br.readLine();
			while (line != null) {
				this.analyseText(line);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			Log.e(TAG, e.getMessage());
		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
		}
	}
}
