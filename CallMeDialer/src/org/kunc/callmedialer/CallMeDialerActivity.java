package org.kunc.callmedialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class CallMeDialerActivity extends Activity {
	Uri data;

	String DEBUG_LOG_TAG = this.getPackageName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String x = getIntent().getData().toString();
		String[] m = x.split(":");
		if (m.length < 1) {
			Log.e(DEBUG_LOG_TAG, "phone number url not correct");
			return;
		}
		if (m[1].startsWith("%2B420")) {
			Log.i(DEBUG_LOG_TAG, "Number prefix cleared");
			m[1] = m[1].substring(6);
		}
		data = Uri.parse("tel:*101*" + m[1] + Uri.encode("#"));
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(data);
		startActivity(callIntent);
		finish();
	}
}