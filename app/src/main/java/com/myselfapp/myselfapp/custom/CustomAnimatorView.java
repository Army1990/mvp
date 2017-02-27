package com.myselfapp.myselfapp.custom;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.myselfapp.myselfapp.bean.MyPoint;

/**
 * Created by Army on 2017/1/17
 */

public class CustomAnimatorView extends View {
    private static final float RADIUS = 50F;
    private Paint point;
    private MyPoint Mypoint;

    public CustomAnimatorView(Context context) {
        super(context);
        point = new Paint(Paint.ANTI_ALIAS_FLAG);
        point.setColor(Color.RED);
    }

    public CustomAnimatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        point = new Paint(Paint.ANTI_ALIAS_FLAG);
        point.setColor(Color.RED);
    }

    public CustomAnimatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        point = new Paint(Paint.ANTI_ALIAS_FLAG);
        point.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Mypoint == null) {
            Mypoint = new MyPoint(RADIUS, RADIUS);
            canvas.drawCircle(Mypoint.getX(), Mypoint.getY(), RADIUS, point);
            setAnimator();

        } else {
            canvas.drawCircle(Mypoint.getX(), Mypoint.getY(), RADIUS, point);
        }
    }

    private void setAnimator() {
        MyPoint point1 = new MyPoint(RADIUS, RADIUS);
        MyPoint point2 = new MyPoint(getWidth()-RADIUS, getHeight()-RADIUS);
        TypeEvaluator evaluator = new PointEvaluator();
        ValueAnimator ObAnim = ObjectAnimator.ofObject(evaluator, point1, point2);
        ObAnim.setDuration(5000);
        ObAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Mypoint = (MyPoint) animation.getAnimatedValue();
                invalidate();
            }
        });
        ObAnim.start();

    }
}
