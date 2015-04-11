package com.example.day0803_1openglesbase;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceView extends GLSurfaceView {

	public MyGLSurfaceView(Context context) {
		super(context);
		this.setEGLContextClientVersion(2);
		/**������Ⱦ��*/
		this.setRenderer(new MyRender(context));
		/**
		 * GLSurfaceView��������Ⱦģʽ��
			����Ⱦ��������Ⱦ(Ĭ��)
			ָ����Ⱦģʽ:
			this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//һֱ�ڻ�
			this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);//����Ϊ����Ⱦ��ֻ
			��������Ƶ�ʱ�򣬲Ž��л��ơ�
			����Ⱦֻ����setRender()֮����òſ��ԡ�
			����Ⱦֻ�е���requestRender()�Ž��л��ơ�
		 */
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
}
