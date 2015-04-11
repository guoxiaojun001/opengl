package com.anjoyo.opengles;

import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;

public class MainActivity extends Activity {
	MyRender render;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyGLSurfaceView view = new MyGLSurfaceView(this);
		render = new MyRender();//‰÷»æ∆˜
		view.setRenderer(render);
		setContentView(view);
	}
	float step = 5f;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		//À≥ ±’Î -step
		//ƒÊ ±’Î +step
		case KeyEvent.KEYCODE_DPAD_LEFT:
			render.angleY -= step;
			return true;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			render.angleY += step;
			return true;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			render.angleX += step;
			return true;
		case KeyEvent.KEYCODE_DPAD_UP:
			render.angleX -= step;
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	float startX,startY;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			float x = event.getX();
			float y = event.getY();
			if(x-startX<-10){
				render.angleY -= step;
			}else if(x-startX>10){
				render.angleY += step;
			}
			if(y-startY<-10){
				render.angleX -= step;
			}else if(y-startY>10){
				render.angleX += step;
			}
			break;
		}
		return true;
	}
}
