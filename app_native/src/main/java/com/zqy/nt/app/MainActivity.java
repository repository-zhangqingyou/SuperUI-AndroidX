package com.zqy.nt.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity implements View.OnClickListener {
    private ImageView mIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setContentView(   ResourcesUtil.getLayoutId("activity_main"));
        initView();
    }

    private void initView() {
       mIcon = findViewById(R.id.icon);
     //   mIcon = findViewById(ResourcesUtil.getId("icon"));
//        mIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,ActivityReplaceResourcesId.class));
//
//            }
//        });

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
