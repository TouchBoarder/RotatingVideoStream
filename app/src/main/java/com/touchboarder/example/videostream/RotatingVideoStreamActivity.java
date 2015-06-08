package com.touchboarder.example.videostream;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * 
 * @author hsigmond touchboarder.com
 * 
 */
public class RotatingVideoStreamActivity extends Activity implements
OnCompletionListener, OnPreparedListener {

	final String TAG = RotatingVideoStreamActivity.class.getSimpleName();
	private VideoView videoView;
	private MediaController mediaController;
	private ProgressBar progressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.videoview);

		Bundle extras = getIntent().getExtras();
		// default test path from Raw folder
		String path = "android.resource://" + getPackageName() + "/"
				+ R.raw.bootanimation_nexus;

		TextView label = (TextView) findViewById(R.id.textView);

		if (extras != null) {
			path = extras.getString("path");
			label.setText(path);
		}

		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		playVideo(path);

	}

	private void playVideo(String path) {
		try {
			videoView = (VideoView) findViewById(R.id.surface);
			mediaController = new MediaController(this);
			videoView.setVideoURI(Uri.parse(path));
			videoView.setOnCompletionListener(this);
			videoView.setOnPreparedListener(this);
			videoView.setMediaController(mediaController);
		} catch (Exception e) {
			// Log.e(TAG, "error: " + e.getMessage(), e);
		}
	}

	public void onCompletion(MediaPlayer arg0) {
		// exit
		onBackPressed();

	}

	public void onPrepared(MediaPlayer mediaplayer) {
		startVideoPlayback();
		mediaController.show(15000);
		progressBar.setVisibility(View.GONE);
	}

	@Override
	protected void onPause() {
		super.onPause();
		releaseMediaPlayer();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		releaseMediaPlayer();

	}

	private void releaseMediaPlayer() {
		if (videoView != null) {
			videoView = null;
		}
	}

	private void startVideoPlayback() {
		if (videoView != null) {
			videoView.start();
		}

	}

	public void onClick(View v) {
		onBackPressed();
	}

}
