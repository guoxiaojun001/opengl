package com.example.day0803_1openglesbase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.Log;

public class ShaderUtil {
	/**
	 * ����program
	 * @param vertexSource ������ɫ����Դ��
	 * @param fragSource ƬԪ��ɫ����Դ��
	 * @return
	 */
	public static int createProgram(String vertexSource,String fragSource){
		int vertexShader = createShader(GLES20.GL_VERTEX_SHADER, vertexSource);
		if(vertexShader == 0){
			return 0;
		}
		int fragShader = createShader(GLES20.GL_FRAGMENT_SHADER, fragSource);
		if(fragShader == 0){
			return 0;
		}
		int program = 0;
		//��������
		program = GLES20.glCreateProgram();
		if(program!=0){//���program�����ɹ�
			//������program
			GLES20.glAttachShader(program, vertexShader);
			//������
			checkError("����������ɫ��");
			GLES20.glAttachShader(program, fragShader);
			//������
			checkError("����ƬԪ��ɫ��");
			//����
			GLES20.glLinkProgram(program);
			//��������Ƿ����
			int[] link = new int[1];//��ѯһ�����ӵ�״̬
			GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, link,0);
			if(link[0] == 0){//������ӳ���
				Log.e("GLES_ERROR", GLES20.glGetProgramInfoLog(program));
				GLES20.glDeleteProgram(program);
				program = 0;
			}
		}
		return program;
	}
	private static void checkError(String op){
		int error ;
		if((error = GLES20.glGetError())!= GLES20.GL_NO_ERROR){
			Log.e("GLES_ERROR", op+": error = "+ error);
			throw new  RuntimeException(op+": error = "+ error);
		}
	}
	/**
	 * ����shader
	 * @param shaderType ��ɫ���ͣ���Ϊ��������ɫ����ƬԪ��ɫ
		//		GLES20.GL_FRAGMENT_SHADER
		//		GLES20.GL_VERTEX_SHADER
	 * @param source ��Ӧ����ɫ������Դ����
	 * @return ��ɫ������
	 */
	private static int createShader(int shaderType,String source){
		int shader = 0;//Ĭ��ֵΪ0.��������ɹ����0
		shader = GLES20.glCreateShader(shaderType);//����shader
		if(shader != 0){//shader�����ɹ�
			//ָ��Դ��
			GLES20.glShaderSource(shader, source);
			//����
			GLES20.glCompileShader(shader);
			int[] compile = new int[1];//ֻ��ѯһ������Ľ��
			//��ȡ������״̬
			//��һ��������shader�ĵ�ַ���ڶ���������ָ����ȡcompile����Ϣ
			//��������������Ž�������顣���ĸ��������������ʲôλ�ÿ�ʼ�档(ƫ����)
			GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compile, 0);
			//�鿴������
			if(compile[0] == 0){//�������
				Log.e("GLES_ERROR", "���ܱ�����ɫԴ�ļ� shaderType="+shaderType);
				Log.e("GLES_ERROR", GLES20.glGetShaderInfoLog(shader));
				//ɾ��shader
				GLES20.glDeleteShader(shader);
				shader = 0;
			}
		}
		return shader;
	}
	
	/**
	 * ����asset�µ���ɫ������
	 */
	public static String loadAssests(String fileName,Resources res){
		String result = null;
		try {
			AssetManager am = res.getAssets();
			InputStream is = am.open(fileName);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int ch;
			while((ch = is.read())!=-1){
				baos.write(ch);
			}
			result = baos.toString("GBK");
			result = result.replaceAll("\r\n", "\n");
			is.close();
			baos.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
