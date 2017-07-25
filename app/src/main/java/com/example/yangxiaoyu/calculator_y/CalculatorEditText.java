package com.example.yangxiaoyu.calculator_y;

import android.content.Context;
import android.content.res.TypedArray;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
/**
 * Created by yangxiaoyu on 17-7-12.
 */

public class CalculatorEditText extends android.support.v7.widget.AppCompatEditText {
    public float mMaximumTextSize;
    public float mMinimumTextSize;
    public float mStepTextSize;

    private final static ActionMode.Callback NO_SELECTION_ACTION_MODE_CALLBACK =
            new ActionMode.Callback() {
                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    return false;
                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    // Prevents the selection action mode on double tap.
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }
            };

    public CalculatorEditText(Context context) {
        this(context,null);
    }

    public CalculatorEditText(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CalculatorEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CalculatorEditText,defStyleAttr,0);
        mMaximumTextSize = a.getDimension(R.styleable.CalculatorEditText_maxTextSize,getTextSize());
        mMinimumTextSize = a.getDimension(R.styleable.CalculatorEditText_minTextSize,getTextSize());
        mStepTextSize = a.getDimension(R.styleable.CalculatorEditText_stepTextSize,(mMaximumTextSize-mMinimumTextSize)/3);
        a.recycle();
        setCustomSelectionActionModeCallback(NO_SELECTION_ACTION_MODE_CALLBACK);
        if(isFocusable()){
            setMovementMethod(ScrollingMovementMethod.getInstance());
        }
        setTextSize(TypedValue.COMPLEX_UNIT_PX,mMaximumTextSize);
    }

    private final Paint mTempPaint = new TextPaint();
    private int mWidthConstraint = -1;
    private final Rect mTempRect = new Rect();
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getActionMasked() == MotionEvent.ACTION_UP){
            cancelLongPress();
        }
        return super.onTouchEvent(event);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        mWidthConstraint = MeasureSpec.getSize(widthMeasureSpec)-getPaddingLeft()-getPaddingRight();
        setTextSize(TypedValue.COMPLEX_UNIT_PX,getVariableTextSize(getText().toString()));
    }

    private float getVariableTextSize(String text) {
        if(mWidthConstraint < 0 || mMaximumTextSize <= mMinimumTextSize){
            return getTextSize();
        }

        mTempPaint.set(getPaint());
        float lastFitTextSize = mMinimumTextSize;
        while (lastFitTextSize < mMaximumTextSize){
            final float nextSize = Math.min(lastFitTextSize+mStepTextSize,mMaximumTextSize);
            mTempPaint.setTextSize(nextSize);
            if(mTempPaint.measureText(text)>mWidthConstraint){
                break;
            }else {
                lastFitTextSize = nextSize;
            }
        }
        return lastFitTextSize;
    }
    @Override
    public int getCompoundPaddingTop() {
        getPaint().getTextBounds("H", 0, 1, mTempRect);

        final Paint.FontMetricsInt fontMetrics = getPaint().getFontMetricsInt();
        final int paddingOffset = -(fontMetrics.ascent + mTempRect.height());
        return super.getCompoundPaddingTop() - Math.min(getPaddingTop(), paddingOffset);
    }

    @Override
    public int getCompoundPaddingBottom() {
        // Measure the bottom padding from the baseline of the text instead of the bottom, but don't
        // remove more than the available bottom padding otherwise clipping may occur.
        final Paint.FontMetricsInt fontMetrics = getPaint().getFontMetricsInt();
        return super.getCompoundPaddingBottom() - Math.min(getPaddingBottom(), fontMetrics.descent);
    }

    public interface OnTextSizeChangeListener {
        void onTextSizeChanged(TextView textView, float oldSize);
    }


}
