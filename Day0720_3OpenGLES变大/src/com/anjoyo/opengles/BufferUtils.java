package com.anjoyo.opengles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

public class BufferUtils {
	public static FloatBuffer arr2FloatBuffer(float[] buffer){
		//一个float占4个byte大小
		ByteBuffer ibb = ByteBuffer.allocateDirect(buffer.length*4);
		//排序
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
			fbb.put(buffer);
		fbb.position(0);//缓冲区数据从第0个位置开始
		return fbb;
	}
	public static FloatBuffer list2FloatBuffer(List<Float> buffer){
		//一个float占4个byte大小
		ByteBuffer ibb = ByteBuffer.allocateDirect(buffer.size()*4);
		//排序
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		for(Float f:buffer){
			fbb.put(f);
		}
		fbb.position(0);//缓冲区数据从第0个位置开始
		return fbb;
	}
}
