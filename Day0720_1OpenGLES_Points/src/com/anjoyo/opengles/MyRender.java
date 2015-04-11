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
	//��surface����ʱ
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//��������ɫ
		gl.glClearColor(0f, 0f, 0f, 1f);
		//���ö��㻺����
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
	}
	//��surface��С�����ı�ʱ
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		radio = (float)width/height;//�Ѹ߶���Ϊ1������Ϊradio
		//ָ���������
		gl.glViewport(0, 0, width, height);
		
		//�ı�״̬������ȡprojection����������ƽ��ͷ��ǰ����ͶӰ����
		gl.glMatrixMode(GL10.GL_PROJECTION);
		//��ʼ��Ϊ��λ����
		gl.glLoadIdentity();
		//ָ��ƽ��ͷ��
		// left rightΪ��ƽ��Ĵ�ֱ����ȥΪ-radio��radio
		// bottom topΪ��ƽ�洹ֱ����ȥ�����������߾��� -1��1
		//zNear,zFar��ƽ���Զƽ�档
		gl.glFrustumf(-radio, radio, -1, 1, 3, 7);
		
	}
	//����
	@Override
	public void onDrawFrame(GL10 gl) {
		//�����ɫ����������ʹ����onSurfaceCreate���������õ���ɫ������
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//����ģ����ͼ����
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();//���ص�λ����
		//��������(���)λ��
		//eyeX, eyeY, eyeZΪ����λ��
//		centerX, centerY, centerZ����߿�
		//upX, upY, upZ���������
		GLU.gluLookAt(gl, 0f, 0f, 5f, 0f, 0f, 0f, 0f, 1f, 0f);
		
		//��һ��������
		float[] vertextCoords = {
				0f,1f,2f,
				-radio,-1f,2f,
				radio,-1f,2f
		};
		//һ��floatռ4��byte��С
		ByteBuffer ibb = ByteBuffer.allocateDirect(vertextCoords.length*4);
		//����
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		fbb.put(vertextCoords);
		fbb.position(0);//���������ݴӵ�0��λ�ÿ�ʼ
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fbb);//ָ������ָ��
		//�������Σ���0λ�ÿ�ʼ���ж��ٸ���vertextCoords.length/3
		
		//������ɫΪ��ɫ
		gl.glColor4f(1f, 0f, 0f, 1f);
		
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vertextCoords.length/3);
		
	}

}
