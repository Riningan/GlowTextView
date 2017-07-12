package com.riningan.glowtextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vadim Akhmarov on 12.07.2017.
 * Project GlowTextViewProject
 * Classname GlowTextView
 * Version 1.0
 * Copyright All rights reserved.
 */

@SuppressLint("AppCompatCustomView")
public class GlowTextView extends TextView {
    private Paint mTextPaint;
    private Paint mCirclePaint;
    private Paint mAlphaPaint;

    private float mGlowRadius;
    private int mGlowColor;


    private Rect mBounds = new Rect();
    private ArrayList<String> mRows = new ArrayList<>();

    public GlowTextView(Context context) {
        super(context);
        init(null);
    }

    public GlowTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GlowTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override
    public void setTextSize(float size) {
        mTextPaint.setTextSize(size);
        super.setTextSize(size);
    }

    @Override
    public void setTextSize(int unit, float size) {
        mTextPaint.setTextSize(size);
        super.setTextSize(unit, size);
    }

    @Override
    public void setTypeface(Typeface tf) {
        mTextPaint.setTypeface(tf);
        super.setTypeface(tf);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        mTextPaint.setTypeface(tf);
        super.setTypeface(tf, style);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap circleBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas circleCanvas = new Canvas(circleBitmap);

        Layout layout = getLayout();
        String text = getText().toString();
        int start = 0;
        int end;
        mRows.clear();
        for (int i = 0; i < getLineCount(); i++) {
            end = layout.getLineEnd(i);
            mRows.add(text.substring(start, end));
            start = end;
        }

        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float top = getPaddingTop() + fm.descent - fm.ascent * 0.9f;
        float left;
        for (String row : mRows) {
            if (row.length() == 0) {
                continue;
            }
            left = getPaddingLeft();
            for (int i = 0; i < row.length(); i++) {
                String curChar = String.valueOf(row.charAt(i));
                float curCharWidth = mTextPaint.measureText(curChar);
                if (!curChar.equals(" ")) {
                    mTextPaint.getTextBounds(curChar, 0, 1, mBounds);
                    circleCanvas.drawCircle(left + (mBounds.right + mBounds.left) / 2, top + (mBounds.bottom + mBounds.top) / 2, mGlowRadius, mCirclePaint);
                }
                left += curCharWidth;
            }
            top += getLineHeight();
        }

        Bitmap alphaBitmap = circleBitmap.extractAlpha();
        circleBitmap.recycle();

        canvas.drawBitmap(alphaBitmap, 0, 0, mAlphaPaint);
        alphaBitmap.recycle();

        super.onDraw(canvas);
    }


    public float getGlowRadius() {
        return mGlowRadius;
    }

    public void setGlowRadius(float glowRadius) {
        mGlowRadius = glowRadius;
        postInvalidate();
    }


    @ColorInt
    public int getGlowColor() {
        return mGlowColor;
    }

    public void setGlowColor(@ColorInt int glowColor) {
        mGlowColor = glowColor;
        mAlphaPaint.setColor(mGlowColor);
        postInvalidate();
    }


    private void init(@Nullable AttributeSet attrs) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        if (attrs == null) {
            mGlowRadius = 60f;
            mGlowColor = Color.WHITE;
        } else {
            @SuppressLint("Recycle")
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.GlowTextView);
            mGlowRadius = a.getDimension(R.styleable.GlowTextView_glowRadius, 60f);
            mGlowColor = a.getColor(R.styleable.GlowTextView_glowColor, Color.WHITE);
        }

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(getTextSize());
        mTextPaint.setTypeface(getTypeface());

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mAlphaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAlphaPaint.setColor(mGlowColor);
        mAlphaPaint.setMaskFilter(new BlurMaskFilter(mGlowRadius, BlurMaskFilter.Blur.NORMAL));
    }
}
