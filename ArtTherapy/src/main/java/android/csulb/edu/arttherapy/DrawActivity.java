package android.csulb.edu.arttherapy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawActivity extends View  {

    private final int paintColor = Color.BLACK;
    private Paint drawPaint;
    private Path path = new Path();

    public void clearCanvas(){
        path.reset();
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path,drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_MOVE:
                // Draws line between last point and this point
                path.lineTo(pointX, pointY);
                break;
            default:
                return false;
        }
        postInvalidate(); // Indicate view should be redrawn
        return true;
    }

    public DrawActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        SetupPaint();
    }

    private void SetupPaint(){
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

}
