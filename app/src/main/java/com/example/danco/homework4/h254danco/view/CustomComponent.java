package com.example.danco.homework4.h254danco.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.example.danco.homework4.h254danco.R;

/**
 * Created by costd035 on 1/31/15.
 */
public class CustomComponent extends View {

    private int leftColor;
    private int rightColor;
    private int paintSize;

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
        init(null, 0);
    }

    public CustomComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomComponent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomAttributes, defStyle, 0);

        rightColor = a.getColor(
                R.styleable.CustomAttributes_rightColor,
                rightColor);

        leftColor = a.getColor(
                R.styleable.CustomAttributes_leftColor,
                leftColor);

        paintSize = a.getDimensionPixelSize(
                R.styleable.CustomAttributes_paintSize,
                paintSize);

        a.recycle();

        // Set up a default TextPaint object
        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        // only painting lines so indicate that so lines drawn correctly.
        paint.setStyle(Paint.Style.STROKE);

        // Update TextPaint and text measurements from attributes
        invalidatePaintAndMeasurements();
    }

    private void invalidatePaintAndMeasurements() {
        paint.setStrokeWidth(paintSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerHeight = h / 2;
        centerWidth = w / 2;
        startHorizontal = ViewCompat.getPaddingStart(this);
        endHorizontal = w - ViewCompat.getPaddingEnd(this);
        topVertical = getPaddingTop();
        bottomVertical = h - getPaddingBottom();

        oval = new RectF(startHorizontal + paintSize/2, topVertical + paintSize/2,
                endHorizontal - paintSize/2, bottomVertical - paintSize/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(leftColor);
        canvas.drawArc(oval, 90, 180, true, paint);
        paint.setColor(rightColor);
        canvas.drawArc(oval, 270, 180, true, paint);
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
