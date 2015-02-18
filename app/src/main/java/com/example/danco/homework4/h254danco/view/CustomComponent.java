package com.example.danco.homework4.h254danco.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.danco.homework4.h254danco.R;

/**
 * Created by costd035 on 1/31/15.
 */
public class CustomComponent extends ImageView {
    private static final String TAG = CustomComponent.class.getSimpleName();

    private int leftColor;
    private int rightColor;
    private int paintSize;
    private int rightColorPercentage = 50;
    private int arcSweepSize;
    private String scaleType;

    RectF oval = new RectF();

    private Paint paint;
    private int centerWidth;
    private int centerHeight;
    private int startHorizontal;
    private int endHorizontal;
    private int topVertical;
    private int bottomVertical;

    public CustomComponent(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public CustomComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public CustomComponent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle, 0);
    }

    // needed this constructor in order to get full credit on assignment
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomComponent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        // Load attributes
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.CustomAttributes, defStyle, defStyleRes);

        rightColor = a.getColor(
                R.styleable.CustomAttributes_rightColor,
                rightColor);

        leftColor = a.getColor(
                R.styleable.CustomAttributes_leftColor,
                leftColor);

        paintSize = a.getDimensionPixelSize(
                R.styleable.CustomAttributes_paintSize,
                paintSize);

        rightColorPercentage = a.getInt(R.styleable.CustomAttributes_rightColorPercentage, 50);
        arcSweepSize = convertToDegrees(rightColorPercentage);

        scaleType = a.getString(R.styleable.CustomAttributes_android_scaleType);

        Log.v(TAG, "arcSweepDegrees=" + arcSweepSize);

        a.recycle();

        // Set up a default TextPaint object
        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        // only painting lines so indicate that so lines drawn correctly.
        paint.setStyle(Paint.Style.STROKE);

        // Update TextPaint and text measurements from attributes
        invalidatePaintAndMeasurements();
    }


    private int convertToDegrees(double arcSweepSize) {
        return (int) (360 * (arcSweepSize / 100));
    }


    private void invalidatePaintAndMeasurements() {
        //see Homework4Solution for properly handling padding.
        paint.setStrokeWidth(paintSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerHeight = h / 2;
        centerWidth = w / 2;

        int scale = Math.min(w, h);
        int horizontalOffset = 0;
        int verticalOffset = 0;

        // handling the scaleType this way is ugly, but I couldn't get
        //  ScaleType.valueOf(scaleType) to work...
        if (scaleType != null && scaleType.equals("7")) {
            if (w > h) {
                horizontalOffset = (w - h) / 2;
            } else if (h > w) {
                verticalOffset = (h - w) / 2;
            }
        }
        startHorizontal = ViewCompat.getPaddingStart(this) + horizontalOffset;
        endHorizontal = scale - ViewCompat.getPaddingEnd(this) + horizontalOffset;
        topVertical = getPaddingTop() + verticalOffset;
        bottomVertical = scale - getPaddingBottom() + verticalOffset;

        oval = new RectF(startHorizontal + paintSize/2, topVertical + paintSize/2,
                endHorizontal - paintSize/2, bottomVertical - paintSize/2);

        super.setScaleType(ScaleType.CENTER_INSIDE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.rotate(-90, centerWidth, centerHeight);
        paint.setColor(rightColor);
        canvas.drawArc(oval, 0, arcSweepSize, false, paint);
        paint.setColor(leftColor);
        canvas.drawArc(oval, arcSweepSize, 360 - arcSweepSize, false, paint);
        canvas.restore();
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getLeftColor() {
        return leftColor;
    }

    /**
     * Gets the example color attribute value.
     *
     * @return The example color attribute value.
     */
    public int getRightColor() {
        return rightColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setLeftColor(int exampleColor) {
        leftColor = exampleColor;
        invalidatePaintAndMeasurements();
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     *
     * @param exampleColor The example color attribute value to use.
     */
    public void setRightColor(int exampleColor) {
        rightColor = exampleColor;
        invalidatePaintAndMeasurements();
    }

    public int getPaintSize() {
        return paintSize;
    }

    public void setPaintSize(int paintSize) {
        this.paintSize = paintSize;
        invalidatePaintAndMeasurements();
    }

}
