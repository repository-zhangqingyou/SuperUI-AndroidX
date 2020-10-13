package com.zqy.support.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zqy.http.request.OKRequest;
import com.zqy.http.request.StringCallback;
import com.zqy.sdk.logger.Logger;
import com.zqy.sdk.utils.AppUtils;
import com.zqy.sdk.utils.GlideUtil;
import com.zqy.sdk.utils.JsonUtils;
import com.zqy.sdk.utils.ToastUtil;
import com.zqy.sdk.virtual.EasyProtectorLib;
import com.zqy.sdk.virtual.EmulatorCheckCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivitySdkUtilTest extends Activity implements View.OnClickListener {

    private ImageView mIvImg;
    private RecyclerView mRecyclerView;
    private LinearLayout mLlC;

    private Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdk_util_test);
        initView();
    }

    private void initView() {
        mIvImg = findViewById(R.id.iv_Img);
        mLlC = findViewById(R.id.ll_C);
        mRecyclerView = new RecyclerView(getActivity());
        mLlC.addView(mRecyclerView);
        mIvImg.setOnClickListener(this);
        mRecyclerView.setNestedScrollingEnabled(false);

        List<String> stringList = new ArrayList<>();
        stringList.add("加载图片");
        stringList.add("json工具测试");
        stringList.add("判断是否是虚拟机");
        stringList.add("安卓工具类测试");
        stringList.add("请求测试");
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
                            case "加载图片":
                                GlideUtil.loadImg(mIvImg, "https://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=http%3A%2F%2Fa2.att.hudong.com%2F36%2F48%2F19300001357258133412489354717.jpg&thumburl=https%3A%2F%2Fss0.bdstatic.com%2F70cFuHSh_Q1YnxGkpoWK1HF6hhy%2Fit%2Fu%3D1906469856%2C4113625838%26fm%3D26%26gp%3D0.jpg");
                                // GlideUtil.loadImg(mIvImg, R.mipmap.ic_launcher);
                                //
                                break;
                            case "json工具测试":
                                Map<String, String> map = new HashMap<>();
                                map.put("1", "2");
                                String objectToJson = JsonUtils.objectToJson(map);
                                Logger.d(objectToJson);
                                ToastUtil.toast(objectToJson);
                                break;
                            case "判断是否是虚拟机":
                                boolean inEmulator = EasyProtectorLib.checkIsRunningInEmulator(getActivity(), new EmulatorCheckCallback() {
                                    @Override
                                    public void findEmulator(String emulatorInfo) {
                                        Logger.d("emulatorInfo：" + emulatorInfo);
                                        ToastUtil.toast("emulatorInfo：" + emulatorInfo);
                                    }
                                });
                                Logger.d("是否是虚拟机：" + inEmulator);
                                ToastUtil.toast("是否是虚拟机：" + inEmulator);
                                break;
                            case "安卓工具类测试":
                                String appName = AppUtils.getAppName();
                                Logger.d("appName：" + appName);
                                ToastUtil.toast("appName：" + appName);
                                break;
                            case "请求测试":
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("qudaoNumber", "yueshenghuo100");
                                OKRequest.get("http://zhuan.admin.zhangqingyou.top:8090/news/getappsetting.do", hashMap, new StringCallback() {
                                    @Override
                                    public void onSuccess(String body) {
                                        Logger.d("请求结果：" + body);
                                        ToastUtil.toast("请求结果：" + body);
                                    }
                                });
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
            case R.id.iv_Img:
                break;

        }
    }
}