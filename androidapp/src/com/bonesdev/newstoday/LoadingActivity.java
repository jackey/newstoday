package com.bonesdev.newstoday;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class LoadingActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.loading);
		// Job
		// When job done, switch to main activity
		new Thread(new Runnable() {
			@Override
			public void run () {
				// Job cost 5 secs.
				SystemClock.sleep(1000 * 5);
				startActivity(new Intent(LoadingActivity.this, MainActivity.class));
			}
		}).start();
	}
}
