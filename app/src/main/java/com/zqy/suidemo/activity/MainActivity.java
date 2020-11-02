package com.zqy.suidemo.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.zqy.suidemo.R;
import com.zqy.superui.core.other.drawable.Gradient;
import com.zqy.superui.core.other.drawable.SuperStateListDrawable;
import com.zqy.superui.core.widget.textview.SuperTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SuperTextView mTvTest;
    private AppCompatTextView mAtvContent;
    private Button mBtTest;

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


        mBtTest.setBackground(new SuperStateListDrawable()
                .setClickAlpha(0.5f)
                .setClickEffect(true)
                .setTextColorState(mBtTest, Color.BLUE, Color.BLUE)
                .setRadius(5)
                .setGradient(Color.WHITE, Color.YELLOW, Gradient.LINEAR_GRADIENT, GradientDrawable.Orientation.LEFT_RIGHT)
                .buid());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rbt_test:
//                SRequest.test(new JsonEntityCallback<CodeAndMsg>(CodeAndMsg.class) {
//                    @Override
//                    public void onSuccess(CodeAndMsg codeAndMsg) {
//                        String toJson = new Gson().toJson(codeAndMsg);
//                        mAtvContent.setText(toJson);
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        mAtvContent.setText(response.getException().toString());
//                    }
//                });
//                ServiceLoader<ApiCallbackService> apiCallbackServiceLoader = RequestManage.getApiCallbackServiceLoader();
//                for (ApiCallbackService apiCallbackService : apiCallbackServiceLoader) {
//                    apiCallbackService.onFinish("123456");
//                }
                break;
            case R.id.bt_test://原生Button测试
                break;
            case R.id.atv_content:
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