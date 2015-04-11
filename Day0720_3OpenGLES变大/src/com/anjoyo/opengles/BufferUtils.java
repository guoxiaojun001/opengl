package com.anjoyo.opengles;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

public class BufferUtils {
	public static FloatBuffer arr2FloatBuffer(float[] buffer){
		//һ��floatռ4��byte��С
		ByteBuffer ibb = ByteBuffer.allocateDirect(buffer.length*4);
		//����
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
			fbb.put(buffer);
		fbb.position(0);//���������ݴӵ�0��λ�ÿ�ʼ
		return fbb;
	}
	public static FloatBuffer list2FloatBuffer(List<Float> buffer){
		//һ��floatռ4��byte��С
		ByteBuffer ibb = ByteBuffer.allocateDirect(buffer.size()*4);
		//����
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		for(Float f:buffer){
			fbb.put(f);
		}
		fbb.position(0);//���������ݴӵ�0��λ�ÿ�ʼ
		return fbb;
	}
}
