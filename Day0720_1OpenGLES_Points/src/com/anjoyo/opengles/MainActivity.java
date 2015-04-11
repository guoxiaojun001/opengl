package com.anjoyo.opengles;

import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyGLSurfaceView view = new MyGLSurfaceView(this);
		Renderer renderer = new MyRender();//äÖÈ¾Æ÷
		view.setRenderer(renderer);
		setContentView(view);
	}

}
