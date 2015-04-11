package com.bullit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.example.mysufaceview.Config;
import com.example.mysufaceview.R;

public class Bullet {
	private float x;
	private float y;
	private float vx;
	private float vy;
	private float t = 0;
	private Bitmap bitmap;
	private Explosion explosion;
	private boolean isExplosion = false;
	private SurfaceView sv;

	public Bullet(SurfaceView sv , float x,float y,float vx,float vy){
		super();
		this.sv = sv;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;

		bitmap = BitmapFactory.decodeResource(sv.getResources(), R.drawable.bullet);

	}

	public void drawBullit(Canvas canvas,Paint paint){

		if (isExplosion) {
			explosion.drawExplosion(canvas, paint);
		}else{
			canvas.drawBitmap(bitmap, x,y, paint);
			go();
		}
	}

	private void go(){
		if (x<Config.screenW/2) {
			x+= vx*t;
			y+=vy*t + 0.5*Config.G*t*t;
			t+=0.5;
		}else{
			isExplosion = true;
			explosion = new Explosion(sv, x, y);

		}
	}

}
