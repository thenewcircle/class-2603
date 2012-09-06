package com.qualcomm.compass;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoseView extends ImageView {
	private int orientation=0;

	public RoseView(Context context) {
		super(context);
	}
	
	public RoseView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RoseView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
//		Paint paint = new Paint();
//		paint.setColor(Color.RED);
//		canvas.drawLine(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
		canvas.rotate(360-orientation, canvas.getWidth()/2, canvas.getHeight()/2 );
		super.onDraw(canvas);
	}
	
	public void setOrientation(int orientation) {
		this.orientation = orientation;
		this.invalidate();
	}

}
