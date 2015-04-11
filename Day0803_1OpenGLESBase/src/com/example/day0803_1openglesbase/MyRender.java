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
	 * ����Ĭ��ֵ 
	 */
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//����ˢ��ɫ
		GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1);
		//������Ȳ���
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		triangle = new Triangle(context);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		//��ȡ��߱ȡ���������ָ����ƽ���ã��ý�ƽ��Ŀ�߱ȵ���ratio����֤ͼ�񲻱��Ρ�
		float ratio = (float)width/height;
		//ָ���Ӵ����ӿڣ�.�൱����Ƭ�ĳ�ϴ
		GLES20.glViewport(0, 0, width, height);
		//����ƽ��ͷ�壨������ƽ���Զƽ�棩
		//��ƽ��1��ʾ���۾����ƽ��Ϊ1��Զƽ��Ϊ10,��ʾ���۾���Զƽ���10.
		Matrix.frustumM(Triangle.mProjMatrix, 0, -ratio, ratio, -1, 1, 1, 10);
		//ָ�����(����)λ�ã�����������Ϸ��� 
		Matrix.setLookAtM(Triangle.mVMatrix, 0, 0, 0, 3, 0, 0, 0, 0, 1, 0);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		//�����ɫ����������Ȼ�����
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|GLES20.GL_DEPTH_BUFFER_BIT);
		triangle.drawSelf();
	}

}
