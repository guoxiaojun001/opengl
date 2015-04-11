package com.anjoyo.opengles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRender implements Renderer {
	float radio ;
	public float angleX=0;//沿X轴旋转多少角度
	public float angleY=0;//沿Z轴旋转多少角度
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
		
		gl.glRotatef(angleX, 1, 0, 0);//围绕x轴旋转
		gl.glRotatef(angleY, 0, 1, 0);//围绕y轴旋转
		
		//一个圆
		int count = 64;
		float step = (float) (Math.PI*2/count);
		List<Float> vertextCoords = new ArrayList<Float>();
		float r = 0.5f;
		float z = 0f;
		for(int i=0;i<=count*4;i++){
			float x =(float) (Math.cos(step*i)*r);
			float y =  (float) (Math.sin(step*i)*r);
			r = r +0.2f/64;
			z -= 0.01f;
			vertextCoords.add(x);
			vertextCoords.add(y);
			vertextCoords.add(z);
		}
		//一个float占4个byte大小
		ByteBuffer ibb = ByteBuffer.allocateDirect(vertextCoords.size()*4);
		//排序
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		for(Float f:vertextCoords){
			fbb.put(f);
		}
		fbb.position(0);//缓冲区数据从第0个位置开始
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fbb);//指定顶点指针
		
		//点大小
		gl.glPointSize(4);
		//设置颜色为红色
		gl.glColor4f(1f, 0f, 0f, 1f);
		
		//画点，从0位置开始，有多少个点vertextCoords.length/3
		gl.glDrawArrays(GL10.GL_POINTS, 0, vertextCoords.size()/3);
		
	}

}
