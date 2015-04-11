package com.example.mysufaceview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DeviceTool.getDeviceInfo(this);
		//没有标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		//设置全屏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		DeviceTool.getDeviceInfo(this);

		setContentView(new mysurfaceview(this));
	}

}
