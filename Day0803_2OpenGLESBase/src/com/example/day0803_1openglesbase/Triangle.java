package com.example.day0803_1openglesbase;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

public class Triangle {
	//����Ϊstatic�Ƿ�����MyRender������ͶӰ����������Ӧ���󷽱㡣
	public static float[] mProjMatrix = new float[16];//ƽ��ͷ��  ͶӰ����
	public static float[] mVMatrix = new float[16];//�������

	public float[] mMVPMatrix = new float[16];//�ܱ任���� 4x4���� 
	private float[] mMMatrix = new float[16];//�任���󣬰���ƽ�ơ���ת�����ŵȡ�
	private Context context;

	private int program;//�����ĳ�������
	private String vertexSource;//������ɫ��Դ��
	private String fragSource;//ƬԪ��ɫ��Դ��
	private int mMVPMatrixHandle;//������ɫ�����ܱ任���������
	private int aPositionHandle;//������ɫ���ж���λ�õ�����
	private int aColorHandle;//������ɫ���ж�����ɫ������

	FloatBuffer positionBuffer;//����λ�ö�ӦfloatBuffer
	FloatBuffer colorBuffer;//��ɫ��ӦFloatBuffer


	int vCount = 3;//��ĸ���

	float angleX = 0;//��x����ת�Ƕ�

	public Triangle(Context context){
		this.context = context;
		//��ʼ����������Ͷ�����ɫ
		initData();
		//��ʼ��Shader
		initShader();
	}
	/**
	 * ��ʼ����������Ͷ�����ɫ
	 */
	private void initData() {
		//ָ������
		float[] vertexCoord = new float[]{
				-0.8f,0,0,
				0,-0.8f,0,
				0.8f,0,0
		};
		//תΪ���㻺����������
		//һ��float��Ӧ4��byte,vertexCoord.length��float���ӦvertexCoord.length*4��byte
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertexCoord.length*4);
		//����
		vbb.order(ByteOrder.nativeOrder());
		//תΪFloatBuffer
		positionBuffer = vbb.asFloatBuffer();
		//������Ž�ȥ
		positionBuffer.put(vertexCoord);
		//ָ���������ʲôλ�ÿ�ʼ
		positionBuffer.position(0);

		//ָ��������ɫ
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
	 * ��ʼ��Shader
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
		//��ʼ�� �任���������һ������ת0�ȡ�������ע���¡�
		Matrix.setRotateM(mMMatrix, 0, 0, 1, 0, 0);
		Matrix.translateM(mMMatrix, 0, 0, 0, 1);
		//Χ��(1,0,1)������ת
		Matrix.rotateM(mMMatrix, 0, angleX, 1, 0, 0);
		//��������ɫ����ֵ
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 0, false, getFinalMatrix(mMMatrix),0);
		//ʹ��pointer�ġ���Ϊ���Ǵ�����FloatBuffer.
		//�ڶ���������size��ʾά�ȣ�3ά��x,y,z��
		//������������type��ʾ����ָ����������ʲô����
		//���ĸ�������normalized��ʾ�Ƿ���
		//�����������stride��ʾÿ�����ݵĳߴ硣(x,y,z)ÿ��������4���ֽڣ�ÿ���㹲3*4���ֽ�
		GLES20.glVertexAttribPointer(aPositionHandle, 3, GLES20.GL_FLOAT, false, 3*4, positionBuffer);
		GLES20.glVertexAttribPointer(aColorHandle, 4, GLES20.GL_FLOAT, false, 4*4, colorBuffer);

		//���ö���λ������
		GLES20.glEnableVertexAttribArray(aPositionHandle);
		//���ö�����ɫ����
		GLES20.glEnableVertexAttribArray(aColorHandle);

		//��������
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount);
	}
	//��������ܾ���
	/**
	 * @param spec �任����(���š���ת���ƶ���)
	 */
	private float[] getFinalMatrix(float[] spec){
		//�������
		//��һ�������������������ڶ�����������˵���ӵ�һ��������ʲôλ�ÿ�ʼ����
		Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, spec, 0);
		Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
		return mMVPMatrix;
	}
}
