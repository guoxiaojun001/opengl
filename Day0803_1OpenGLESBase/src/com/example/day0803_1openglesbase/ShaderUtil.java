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
	 * 创建program
	 * @param vertexSource 顶点着色器的源码
	 * @param fragSource 片元着色器的源码
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
		//创建程序
		program = GLES20.glCreateProgram();
		if(program!=0){//如果program创建成功
			//附属到program
			GLES20.glAttachShader(program, vertexShader);
			//检查错误
			checkError("附属顶点着色器");
			GLES20.glAttachShader(program, fragShader);
			//检查错误
			checkError("附属片元着色器");
			//链接
			GLES20.glLinkProgram(program);
			//检查链接是否出错
			int[] link = new int[1];//查询一个链接的状态
			GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, link,0);
			if(link[0] == 0){//如果链接出错
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
	 * 创建shader
	 * @param shaderType 着色类型，分为：顶点着色器和片元着色
		//		GLES20.GL_FRAGMENT_SHADER
		//		GLES20.GL_VERTEX_SHADER
	 * @param source 对应的着色器程序源代码
	 * @return 着色器引用
	 */
	private static int createShader(int shaderType,String source){
		int shader = 0;//默认值为0.如果创建成功则非0
		shader = GLES20.glCreateShader(shaderType);//创建shader
		if(shader != 0){//shader创建成功
			//指定源码
			GLES20.glShaderSource(shader, source);
			//编译
			GLES20.glCompileShader(shader);
			int[] compile = new int[1];//只查询一个编译的结果
			//获取编译结果状态
			//第一个参数，shader的地址。第二个参数，指定获取compile的信息
			//第三个参数，存放结果的数组。第四个参数，从数组的什么位置开始存。(偏移量)
			GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compile, 0);
			//查看编译结果
			if(compile[0] == 0){//编译出错
				Log.e("GLES_ERROR", "不能编译着色源文件 shaderType="+shaderType);
				Log.e("GLES_ERROR", GLES20.glGetShaderInfoLog(shader));
				//删除shader
				GLES20.glDeleteShader(shader);
				shader = 0;
			}
		}
		return shader;
	}
	
	/**
	 * 加载asset下的着色器程序
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
