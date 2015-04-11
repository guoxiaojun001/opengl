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
		//第一步：得到SurfaceView的管理者SurfaceHolder对象
		SurfaceHolder holder = view.getHolder();
		//第二步：锁定画布
		Canvas canvas = holder.lockCanvas();
		//第三步：调回SurfaceView的onDraw方法
		view.onDraw(canvas);
		//第四步：解除锁定并提交更新
		holder.unlockCanvasAndPost(canvas);
	}
}
