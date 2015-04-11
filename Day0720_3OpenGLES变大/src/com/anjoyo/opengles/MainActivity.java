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
		render = new MyRender();//��Ⱦ��
		view.setRenderer(render);
		//��Ⱦģʽ�������� RENDERMODE_CONTINUOUSLY������Ⱦ
		//��RENDERMODE_WHEN_DIRTY����Ⱦ;
		//����Ⱦ��Ҫ�ֶ��������
		//****ע�⣬������setRender֮����ã������׳��쳣��
		view.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		setContentView(view);
	}
	float step = 5f;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		//˳ʱ�� -step
		//��ʱ�� +step
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
		view.requestRender();//������Ⱦ������Ⱦ��Ҫ�ֶ�ȥ����
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
		view.requestRender();//������Ⱦ������Ⱦ��Ҫ�ֶ�ȥ����
		return true;
	}
}
