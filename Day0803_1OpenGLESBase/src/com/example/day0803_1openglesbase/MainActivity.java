package com.example.day0803_1openglesbase;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	private MyGLSurfaceView view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new MyGLSurfaceView(this);
		setContentView(view);
	}
	@Override
	protected void onPause() {
		super.onPause();
		view.onPause();
	}
	@Override
	protected void onResume() {
		super.onResume();
		view.onResume();
	}

}
