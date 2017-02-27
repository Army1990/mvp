package com.myselfapp.myselfapp.custom;

import android.animation.TypeEvaluator;

import com.myselfapp.myselfapp.bean.MyPoint;

/**
 * Created by Army on 2017/1/17
 */

public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        MyPoint startPoint=(MyPoint)startValue;
        MyPoint endPoint=(MyPoint)endValue;
        float pointX=startPoint.getX()+(endPoint.getX()-startPoint.getX())*fraction;
        float pointY=startPoint.getY()+(endPoint.getY()-startPoint.getY())*fraction;
        return new MyPoint(pointX,pointY);
    }
}
