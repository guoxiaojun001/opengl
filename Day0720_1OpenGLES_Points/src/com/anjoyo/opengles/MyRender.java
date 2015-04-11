package com.anjoyo.opengles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRender implements Renderer {
	float radio ;
	//当surface创建时
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//设置清屏色
		gl.glClearColor(0f, 0f, 0f, 1f);
		//启用顶点缓冲区
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
	}
	//当surface大小发生改变时
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		radio = (float)width/height;//把高度视为1，则宽度为radio
		//指定输出窗口
		gl.glViewport(0, 0, width, height);
		
		//改变状态机，获取projection矩阵。在设置平截头体前加载投影矩阵。
		gl.glMatrixMode(GL10.GL_PROJECTION);
		//初始化为单位矩阵
		gl.glLoadIdentity();
		//指定平截头体
		// left right为近平面的垂直看过去为-radio到radio
		// bottom top为近平面垂直看过去的上下两个边距离 -1到1
		//zNear,zFar近平面和远平面。
		gl.glFrustumf(-radio, radio, -1, 1, 3, 7);
		
	}
	//绘制
	@Override
	public void onDrawFrame(GL10 gl) {
		//清除颜色缓冲区，并使用在onSurfaceCreate方法中设置的颜色清屏。
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//加载模型视图矩阵
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();//加载单位矩阵
		//设置眼球(相机)位置
		//eyeX, eyeY, eyeZ为眼球位置
//		centerX, centerY, centerZ朝里边看
		//upX, upY, upZ相机朝向上
		GLU.gluLookAt(gl, 0f, 0f, 5f, 0f, 0f, 0f, 0f, 1f, 0f);
		
		//画一个三角形
		float[] vertextCoords = {
				0f,1f,2f,
				-radio,-1f,2f,
				radio,-1f,2f
		};
		//一个float占4个byte大小
		ByteBuffer ibb = ByteBuffer.allocateDirect(vertextCoords.length*4);
		//排序
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		fbb.put(vertextCoords);
		fbb.position(0);//缓冲区数据从第0个位置开始
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fbb);//指定顶点指针
		//画三角形，从0位置开始，有多少个点vertextCoords.length/3
		
		//设置颜色为红色
		gl.glColor4f(1f, 0f, 0f, 1f);
		
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vertextCoords.length/3);
		
	}

}
