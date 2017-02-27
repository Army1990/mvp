package com.myselfapp.myselfapp.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.myselfapp.myselfapp.R;
import com.myselfapp.myselfapp.bean.MyPoint;
import com.myselfapp.myselfapp.custom.CustomAnimatorView;
import com.myselfapp.myselfapp.mvp.presenter.LoginUserPresenter;
import com.myselfapp.myselfapp.mvp.view.LoginUserView;

public class LoginActivity extends BaseActivity implements LoginUserView {

    private TextView animatorTV;
    private CustomAnimatorView animatorTV1;
    Handler handle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==MODE_PRIVATE){
                animatorTV.setText("11111");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        animatorTV = (TextView) findViewById(R.id.animator);
        animatorTV1 = (CustomAnimatorView) findViewById(R.id.animator1);
        initView();
    }

    private void initView() {
        animatorTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimator();
            }
        });
        new LoginUserPresenter();
        ThreadTest();
    }

    private void ThreadTest() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                    Message msg=new Message();
//                    msg.what=MODE_PRIVATE;
//                    handle.sendMessage(msg);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        handle.post(new Runnable() {
            @Override
            public void run() {
                animatorTV.setText("11111");
            }
        });
    }

    private void setAnimator() {
        ObjectAnimator oa = ObjectAnimator.ofFloat(animatorTV, "rotation", 0f, 100f, 50f, 0f);
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(animatorTV, "alpha", 0f, 50f);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(animatorTV, "alpha", 0f, 50f);
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(animatorTV, "alpha", 0f, 50f);
        AnimatorSet anim = new AnimatorSet();
        anim.play(oa).after(oa1).before(oa2).with(oa3);
        anim.setDuration(5000);
        anim.start();
    }

    private void setAnimator2(){
        MyPoint point1 = new MyPoint(0, 0);
        MyPoint point2 = new MyPoint(100, 100);

    }

    @Override
    public int setId(int id) {
        return 0;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setPhone(String phone) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPhone() {
        return null;
    }
}
