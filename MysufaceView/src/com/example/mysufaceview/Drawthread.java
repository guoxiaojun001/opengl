package com.example.mysufaceview;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Drawthread extends Thread {
	mysurfaceview view;
	
	public Drawthread(SurfaceView view) {
		super();
		this.view = (mysurfaceview) view;
	}

	@SuppressLint("WrongCall")
	@Override
	public void run() {
		//��һ�����õ�SurfaceView�Ĺ�����SurfaceHolder����
		SurfaceHolder holder = view.getHolder();
		//�ڶ�������������
		Canvas canvas = holder.lockCanvas();
		//������������SurfaceView��onDraw����
		view.onDraw(canvas);
		//���Ĳ�������������ύ����
		holder.unlockCanvasAndPost(canvas);
	}
}
