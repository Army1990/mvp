package com.myselfapp.myselfapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.myselfapp.myselfapp.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView textShow;
    int i = 0;
    private TextView animatorTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textShow = (TextView) findViewById(R.id.textShow);
        textShow.setOnClickListener(this);
        init();
    }

    private void init() {
    }

    @Override
    public void onClick(View v) {
        if (i < 9) {
            i++;
            textShow.setText(i + "");
        } else {
            textShow.setText("测试");
        }
    }
}
