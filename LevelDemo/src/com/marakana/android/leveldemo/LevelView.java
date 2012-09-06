package com.marakana.android.leveldemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class LevelView extends View {
	private float[] orientation;
	private Paint paint = new Paint();

	public LevelView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public LevelView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LevelView(Context context) {
		super(context);
	}

	public void setOrientation(float[] orientation) {
		this.orientation = orientation;
		this.invalidate();
	}

	public void onDraw(Canvas c) {
		if(this.orientation==null) return;
		paint.setColor(Color.RED);

		int width = this.getMeasuredWidth();
		int height = this.getMeasuredHeight();

		float pitch = (int) this.orientation[1];
		float roll = (int) this.orientation[2];

		pitch = (pitch < -90) ? -90 : pitch;
		pitch = (pitch > 90) ? 90 : pitch;
		float yMultiplier = (float) ((pitch + 90) / 180.0);

		roll = (roll < -90) ? -90 : roll;
		roll = (roll > 90) ? 90 : roll;
		float xMultiplier = (float) ((roll + 90) / 180.0);

		c.drawCircle(width * xMultiplier, height * yMultiplier, 25, paint);

//		Log.d("Level", String.format("[%d x %f] [%d x %f] ", width, xMultiplier, height,
//				yMultiplier));

	}
}
