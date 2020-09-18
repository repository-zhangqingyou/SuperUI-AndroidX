package com.zqy.nt.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatImageView mIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // ;
    }

    private void initView() {
        mIcon = findViewById(R.id.icon);
        mIcon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.icon:
                break;
        }
    }


}
