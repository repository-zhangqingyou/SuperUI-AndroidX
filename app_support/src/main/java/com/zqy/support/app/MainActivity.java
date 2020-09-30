package com.zqy.support.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.zqy.sdk.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {
    private ImageView mIcon;
    private RecyclerView mRecyclerView;

    private Activity getActivity() {
        return this;
    }

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

        mIcon = findViewById(R.id.icon);
        mIcon.setOnClickListener(this);
        mRecyclerView = findViewById(R.id.recyclerView);

        List<String> stringList = new ArrayList<>();
        stringList.add("sdk工具测试");
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                Button button = new Button(getActivity());
                button.setGravity(Gravity.CENTER);
                button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(button) {
                };
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
                String s = stringList.get(i);
                Button button = (Button) viewHolder.itemView;
                button.setText(s);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (button.getText().toString()) {
                            case "sdk工具测试":
                                startActivity(new Intent(getActivity(),ActivitySdkUtilTest.class));
                                break;
                        }
                    }
                });
            }

            @Override
            public int getItemCount() {
                return stringList.size();
            }
        });
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
