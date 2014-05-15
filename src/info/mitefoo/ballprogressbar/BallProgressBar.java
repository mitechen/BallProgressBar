package info.mitefoo.ballprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BallProgressBar extends View implements Runnable {
	private static final String tag = "BallProgressBar";
	private Paint paint = null;
	private int radius = 50;
	private int viewWidthHalf = -1;
	private int viewHeightHalf = -1;
	private int offSet = 0;
	private int offSetMax = 100;

	private int leftColor = -1;
	private int rightColor = -1;

	private boolean plusFlag = true;
	private boolean isRun = true;

	public BallProgressBar(Context context) {
		super(context);
		init();
	}

	public BallProgressBar(Context context, AttributeSet paramAttributeSet) {
		super(context, paramAttributeSet);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (viewWidthHalf == -1)
			viewWidthHalf = this.getMeasuredWidth() / 2;
		if (viewHeightHalf == -1)
			viewHeightHalf = this.getMeasuredHeight() / 2;

		if (-1 != leftColor)
			paint.setColor(leftColor);
		else
			paint.setColor(Color.rgb(75, 179, 251));
		canvas.drawCircle(viewWidthHalf + offSet, viewHeightHalf, radius, paint);

		if (-1 != rightColor)
			paint.setColor(rightColor);
		else
			paint.setColor(Color.rgb(99, 192, 111));
		canvas.drawCircle(viewWidthHalf - offSet, viewHeightHalf, radius, paint);
	}
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted() && isRun) {
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
			if(plusFlag){
				offSet++;
				if(offSet>=offSetMax)
					plusFlag = false;
			}else{
				offSet--;
				if(offSet<=(offSetMax*-1))
					plusFlag = true;
			}
			postInvalidate();
		}
	}
	
	@Override
	protected void onVisibilityChanged(View changedView, int visibility) {
		super.onVisibilityChanged(changedView, visibility);
		if(isShown()){
			isRun = true;
			new Thread(this).start();
		}else{
			isRun = false;
		}
		Log.i(tag, "onVisibilityChanged="+isShown()+"#isRun="+isRun);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}
	
	public int getLeftColor() {
		return leftColor;
	}

	public void setLeftColor(int leftColor) {
		this.leftColor = leftColor;
	}

	public int getRightColor() {
		return rightColor;
	}

	public void setRightColor(int rightColor) {
		this.rightColor = rightColor;
	}
}
