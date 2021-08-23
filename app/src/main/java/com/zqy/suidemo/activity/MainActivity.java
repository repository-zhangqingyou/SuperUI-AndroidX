package com.zqy.suidemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.zqy.suidemo.R;
import com.zqy.suidemo.model.AppInfo;
import com.zqy.superui.core.enums.Gradient;
import com.zqy.superui.core.other.drawable.SuperStateListDrawable;
import com.zqy.superui.core.widget.framelayout.SuperFrameLayout;
import com.zqy.superui.core.widget.popup.tips.LoadPopup;
import com.zqy.superui.core.widget.textview.SuperTextView;
import com.zqy.superutils.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.List;

import static com.qmuiteam.qmui.layout.IQMUILayout.HIDE_RADIUS_SIDE_BOTTOM;

//
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SuperTextView mTvTest;
    private AppCompatTextView mAtvContent;
    private Button mBtTest;
    private SuperFrameLayout mSuperFrameLayout;
    private QMUIRadiusImageView2 mQMUIRadiusImageView2;
    private LinearLayout mLlR;
    private ImageView mIvR;
    private SuperTextView mTvLottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView() {
        mTvTest = findViewById(R.id.rbt_test);
        mTvTest.setOnClickListener(this);
        mAtvContent = (AppCompatTextView) findViewById(R.id.atv_content);
        mAtvContent.setOnClickListener(this);
        mBtTest = findViewById(R.id.bt_test);
        mBtTest.setOnClickListener(this);
        mSuperFrameLayout = findViewById(R.id.superFrameLayout);
        mQMUIRadiusImageView2 = findViewById(R.id.qMUIRadiusImageView2);
        mQMUIRadiusImageView2.setOnClickListener(this);
        mLlR = findViewById(R.id.ll_R);
        mLlR.setOnClickListener(this);
        mIvR = findViewById(R.id.iv_R);
        mIvR.setOnClickListener(this);
        mTvLottie = findViewById(R.id.tv_Lottie);
        mTvLottie.setOnClickListener(this);
        //  RSAEncrypt.genKeyPair();
//        String s = RSAEncrypt.encryptPublicKey("123");
//        String s1 = RSAEncrypt.decryptPrivateKey(s, RSAEncrypt.PRIVATE_KEY);
        //  mTvTest.setClickEffect(false);

//       int SuperUI_AppTheme = ResourceUtils.getStyleIdByName("Base_DialogWindowTitle_AppCompat");
//        int SuperUI_AppTheme2 = ResourceUtils.getStyleIdByName("Base.DialogWindowTitle.AppCompat");
        // mTvTest.setBackground(getStateListDrawable());


        mTvTest.setGradient(Color.WHITE, Color.YELLOW, Gradient.LINEAR_GRADIENT, GradientDrawable.Orientation.LEFT_RIGHT);
        mTvTest.setTextColorState(Color.BLACK, Color.BLACK);
//        mTvTest.buid();


        //  mTvTest.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
//
//        mBtTest.setBackground(new SuperStateListDrawable()
//                .setClickAlpha(0.5f)
//                .setClickEffect(true)
//                .setTextColorState(mBtTest, Color.BLUE, Color.BLUE)
//                .setRadius(5)
//                .setGradient(Color.WHITE, Color.YELLOW, Gradient.LINEAR_GRADIENT, GradientDrawable.Orientation.LEFT_RIGHT)
//                .buid());

//        Drawable buid = new SuperRippleDrawable()
//                .setRadius(20)
//                //.setGradient(Color.WHITE, Color.YELLOW, Gradient.LINEAR_GRADIENT, GradientDrawable.Orientation.LEFT_RIGHT)
//                .setRipple(Color.BLUE, Color.YELLOW)
//                .buid();
        // mIvR.setBackground(buid);
        // mIvR.setForeground(buid);

        // mBtTest.setBackground(new RippleDrawable());

        mQMUIRadiusImageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mQMUIRadiusImageView2.setRadiusAndShadow(SizeUtils.dp2px(5),
                HIDE_RADIUS_SIDE_BOTTOM, 0, 0);


        String json = ResourceUtils.readAssets2String("appinfo.txt");
        String analyze = analyze(json);
        if (!TextUtils.isEmpty(analyze)) {
            Type type = new ParameterizedTypeImpl(AppInfo.class);
            List<AppInfo> appInfoList = new Gson().fromJson(analyze, type);
            Log.d("json", appInfoList.toString());
        }

    }

    /**
     * @param json
     * @return
     */
    private String analyze(String json) {
        String replaceAll = json.replaceAll("\r", "").replaceAll("\n", "");
        StringBuilder stringBuilder = new StringBuilder();
        if (replaceAll.contains("}{")) {
            String[] split = replaceAll.split("\\}\\{");
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                if (i < split.length - 1) {
                    if (i == 0) {
                        stringBuilder.append("[").append(s).append("},");
                    } else {
                        stringBuilder.append("{").append(s).append("},");
                    }
                } else {
                    stringBuilder.append("{").append(s).append("]");
                }
            }
        } else {
            stringBuilder.append("[").append(replaceAll).append("]");
        }
        return stringBuilder.toString();
    }


    boolean isShow = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rbt_test:
                LoadPopup loadPopup = new LoadPopup(this);
                loadPopup.show(mTvTest);
                break;
            case R.id.bt_test://原生Button测试
                //VisibilityAnimationUtil.setVisibilityLeft(mSuperFrameLayout, 500, isShow = !isShow);
                break;
            case R.id.atv_content:
                break;

            case R.id.qMUIRadiusImageView2:
                break;
            case R.id.iv_R:
                break;
            case R.id.tv_Lottie://Lottie动画
                startActivity(new Intent(this, LottieActivity.class));
                break;
        }
    }


    /**
     * 单背景样式
     *
     * @return
     */
    private Drawable getStateListDrawable() {
        Drawable stateListDrawable = new SuperStateListDrawable().setClickAlpha(0.6f)//设置点击后透明度
                .setRadius(10)//圆角
                // .setStrokeColorAndWidth(1, Color.RED)
                // .setSolidColor(ContextCompat.getColor(this, R.color.colorAccent))//背景颜色
                .setGradient(ContextCompat.getColor(this, R.color.colorAccent), ContextCompat.getColor(this, R.color.colorPrimaryDark), Gradient.LINEAR_GRADIENT, GradientDrawable.Orientation.LEFT_RIGHT)
                .buid();
        return stateListDrawable;
    }
}