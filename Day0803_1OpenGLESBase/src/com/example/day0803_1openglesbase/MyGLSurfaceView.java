package com.example.day0803_1openglesbase;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceView extends GLSurfaceView {

	public MyGLSurfaceView(Context context) {
		super(context);
		this.setEGLContextClientVersion(2);
		/**设置渲染器*/
		this.setRenderer(new MyRender(context));
		/**
		 * GLSurfaceView有两种渲染模式：
			脏渲染、持续渲染(默认)
			指定渲染模式:
			this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//一直在画
			this.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);//设置为脏渲染，只
			用请求绘制的时候，才进行绘制。
			脏渲染只有在setRender()之后调用才可以。
			脏渲染只有调用requestRender()才进行绘制。
		 */
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
}
