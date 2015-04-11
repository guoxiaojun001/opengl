package com.bullit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.example.mysufaceview.R;

public class Explosion {
	private float x;
	private float y;
	private int index;
	private Bitmap[] bitmap;
	
	public Explosion(SurfaceView sv ,float x, float y){
		this.x = x;
		this.y = y;
		bitmap = new Bitmap[]{
			BitmapFactory.decodeResource(sv.getResources(), R.drawable.explode0),
			BitmapFactory.decodeResource(sv.getResources(), R.drawable.explode1),
			BitmapFactory.decodeResource(sv.getResources(), R.drawable.explode2),
			BitmapFactory.decodeResource(sv.getResources(), R.drawable.explode3),
			BitmapFactory.decodeResource(sv.getResources(), R.drawable.explode4),
			BitmapFactory.decodeResource(sv.getResources(), R.drawable.explode5)
		};
		index = 0;
	}
	
	public void drawExplosion(Canvas canvas,Paint paint){
		
		if (index >= bitmap.length) {
			//do nothing
		}else{
			canvas.drawBitmap(bitmap[index], x,y, paint);
			index++;
		}
		
	}

}
