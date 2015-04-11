package com.example.day0803_1openglesbase;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;

public class MyRender implements Renderer {
	private Triangle triangle;
	private Context context;
	public MyRender(Context context){
		this.context = context;
	}
	/**
	 * 设置默认值 
	 */
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//设置刷屏色
		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1);
		//启用深度测试
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		triangle = new Triangle(context);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		//获取宽高比。将来用于指定近平面用，让近平面的宽高比等于ratio，保证图像不变形。
		float ratio = (float)width/height;
		//指定视窗（视口）.相当于照片的冲洗
		GLES20.glViewport(0, 0, width, height);
		//设置平截头体（包含近平面和远平面）
		//近平面1表示人眼距离近平面为1，远平面为10,表示人眼距离远平面的10.
		Matrix.frustumM(Triangle.mProjMatrix, 0, -ratio, ratio, -1, 1, 1, 10);
		//指定相机(人眼)位置，朝向，相机向上方向 
		Matrix.setLookAtM(Triangle.mVMatrix, 0, 0, 0, 3, 0, 0, 0, 0, 1, 0);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		//清除颜色缓冲区和深度缓冲区
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|GLES20.GL_DEPTH_BUFFER_BIT);
		triangle.drawSelf();
	}

}
