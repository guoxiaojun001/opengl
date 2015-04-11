package com.anjoyo.opengles;

import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;

public class MainActivity extends Activity {
	MyRender render;
	MyGLSurfaceView view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new MyGLSurfaceView(this);
		render = new MyRender();//渲染器
		view.setRenderer(render);
		//渲染模式：有两个 RENDERMODE_CONTINUOUSLY持续渲染
		//和RENDERMODE_WHEN_DIRTY脏渲染;
		//脏渲染需要手动请求绘制
		//****注意，必须在setRender之后调用，否则抛出异常。
		view.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		setContentView(view);
	}
	float step = 5f;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		//顺时针 -step
		//逆时针 +step
		case KeyEvent.KEYCODE_DPAD_LEFT:
			render.angleY -= step;
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			render.angleY += step;
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			render.angleX += step;
			break;
		case KeyEvent.KEYCODE_DPAD_UP:
			render.angleX -= step;
			break;
		}
		view.requestRender();//请求渲染。脏渲染需要手动去请求
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
		view.requestRender();//请求渲染。脏渲染需要手动去请求
		return true;
	}
}
