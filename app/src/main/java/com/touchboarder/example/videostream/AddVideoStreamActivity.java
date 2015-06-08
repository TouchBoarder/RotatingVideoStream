package com.touchboarder.example.videostream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * 
 * @author hsigmond touchboarder.com
 * 
 */
public class AddVideoStreamActivity extends Activity {
	private String path = "";
	private EditText editText;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editText = (EditText) findViewById(R.id.editText);

		playVideo(path);
	}

	public void onClick(View v) {
		// Streaming: try another URL here to test.
		playVideo(editText.getText().toString());
	}

	private void playVideo(String path) {
		Intent intent = new Intent(this, RotatingVideoStreamActivity.class);
		if (!TextUtils.isEmpty(path))
			intent.putExtra("path", path);
		startActivity(intent);
		overridePendingTransition(0, 0);
	}

}
