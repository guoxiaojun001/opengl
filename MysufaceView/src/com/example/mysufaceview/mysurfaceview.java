package com.example.mysufaceview;

import com.bullit.Bullet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class mysurfaceview extends SurfaceView implements SurfaceHolder.Callback {
	private SurfaceHolder holder;
	private Bitmap bitmap;
	private boolean flag  = true;
	private Bullet bullet;
	private Paint paint;

	public mysurfaceview(Context context) {
		super(context);
		holder = getHolder();
		holder.addCallback(this);

		bullet = new Bullet(this, 0, Config.screenH, 2.5f, -5.0f);

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		bitmap = DeviceTool.resizeBitmap(bitmap);
		new MyThread().start();

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		flag = false;

	}

	public class MyThread extends Thread{
		public void run(){
			while (flag){
				long start = System.currentTimeMillis();
				Canvas canvas = holder.lockCanvas();
				if(canvas != null){
					onDraw(canvas);
					holder.unlockCanvasAndPost(canvas);
				}

				long end = System.currentTimeMillis();
				if (end - start < 40) {
					try {
						Thread.sleep(40- (end - start));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}

	}

	public void onDraw(Canvas canvas){
		//ÇåÆÁ
		canvas.drawBitmap(bitmap, 0,0, null);
		bullet.drawBullit(canvas, paint);
	}
}

