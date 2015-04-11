package com.example.mysufaceview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DeviceTool {

	//get width height
	public static void getDeviceInfo(Activity activity){
		WindowManager windowManager = activity.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);

		Config.screenW = outMetrics.widthPixels;
		Config.screenH = outMetrics.heightPixels;
	}

	//Ëõ·Å
	public static Bitmap resizeBitmap(Bitmap bitmap){

		Matrix matrix = new Matrix();
		float sx = (float)Config.screenW/bitmap.getWidth();
		float sy = (float)Config.screenH/bitmap.getHeight();

		matrix.setScale(sx, sy);
		Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);

		return b;
	}
	//
}
