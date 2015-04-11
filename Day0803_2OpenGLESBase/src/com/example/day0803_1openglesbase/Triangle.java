package com.example.day0803_1openglesbase;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

public class Triangle {
	//声明为static是方便在MyRender中设置投影矩阵和相机对应矩阵方便。
	public static float[] mProjMatrix = new float[16];//平截头体  投影矩阵
	public static float[] mVMatrix = new float[16];//相机矩阵

	public float[] mMVPMatrix = new float[16];//总变换矩阵 4x4矩阵 
	private float[] mMMatrix = new float[16];//变换矩阵，包括平移、旋转、缩放等。
	private Context context;

	private int program;//创建的程序引用
	private String vertexSource;//顶点着色器源码
	private String fragSource;//片元着色器源码
	private int mMVPMatrixHandle;//顶点着色器中总变换矩阵的引用
	private int aPositionHandle;//顶点着色器中顶点位置的引用
	private int aColorHandle;//顶点着色器中顶点颜色的引用

	FloatBuffer positionBuffer;//顶点位置对应floatBuffer
	FloatBuffer colorBuffer;//颜色对应FloatBuffer


	int vCount = 3;//点的个数

	float angleX = 0;//绕x轴旋转角度

	public Triangle(Context context){
		this.context = context;
		//初始化顶点坐标和顶点颜色
		initData();
		//初始化Shader
		initShader();
	}
	/**
	 * 初始化顶点坐标和顶点颜色
	 */
	private void initData() {
		//指定坐标
		float[] vertexCoord = new float[]{
				-0.8f,0,0,
				0,-0.8f,0,
				0.8f,0,0
		};
		//转为顶点缓冲数据类型
		//一个float对应4个byte,vertexCoord.length个float则对应vertexCoord.length*4个byte
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertexCoord.length*4);
		//排序
		vbb.order(ByteOrder.nativeOrder());
		//转为FloatBuffer
		positionBuffer = vbb.asFloatBuffer();
		//将数组放进去
		positionBuffer.put(vertexCoord);
		//指定从数组的什么位置开始
		positionBuffer.position(0);

		//指定顶点颜色
		float[] colors = new float[]{
				1,0,0,1,
				0,1,0,1,
				0,0,0,1
		};
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(colors);
		colorBuffer.position(0);
	}
	/**
	 * 初始化Shader
	 */
	private void initShader() {
		vertexSource = ShaderUtil.loadAssests("vertex.sh", context.getResources());
		fragSource = ShaderUtil.loadAssests("frag.sh", context.getResources());
		program = ShaderUtil.createProgram(vertexSource, fragSource);
		mMVPMatrixHandle = GLES20.glGetUniformLocation(program, "mMVPMatrix");
		aPositionHandle = GLES20.glGetAttribLocation(program, "aPosition");
		aColorHandle = GLES20.glGetAttribLocation(program, "aColor");
	}

	public void drawSelf(){
		GLES20.glUseProgram(program);
		//初始化 变换矩阵。绕随便一个轴旋转0度。方法名注意下。
		Matrix.setRotateM(mMMatrix, 0, 0, 1, 0, 0);
		Matrix.translateM(mMMatrix, 0, 0, 0, 1);
		//围绕(1,0,1)向量旋转
		Matrix.rotateM(mMMatrix, 0, angleX, 1, 0, 0);
		//给顶点着色器赋值
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 0, false, getFinalMatrix(mMMatrix),0);
		//使用pointer的。因为我们创建了FloatBuffer.
		//第二个参数，size表示维度（3维：x,y,z）
		//第三个参数，type表示顶点指针中数据是什么类型
		//第四个参数，normalized表示是否规格化
		//第五个参数，stride表示每组数据的尺寸。(x,y,z)每个浮点数4个字节，每个点共3*4个字节
		GLES20.glVertexAttribPointer(aPositionHandle, 3, GLES20.GL_FLOAT, false, 3*4, positionBuffer);
		GLES20.glVertexAttribPointer(aColorHandle, 4, GLES20.GL_FLOAT, false, 4*4, colorBuffer);

		//启用顶点位置数据
		GLES20.glEnableVertexAttribArray(aPositionHandle);
		//启用顶点颜色数据
		GLES20.glEnableVertexAttribArray(aColorHandle);

		//画三角形
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount);
	}
	//计算最后总矩阵
	/**
	 * @param spec 变换矩阵(缩放、旋转、移动等)
	 */
	private float[] getFinalMatrix(float[] spec){
		//矩阵相乘
		//第一个参数用来保存结果，第二个参数用来说明从第一个参数的什么位置开始保存
		Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, spec, 0);
		Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
		return mMVPMatrix;
	}
}
