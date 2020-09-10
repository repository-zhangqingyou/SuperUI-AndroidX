package com.zqy.gamemange.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.zqy.baserequest.bean.DescAndCode;
import com.zqy.baserequest.request.JsonEntityCallback;
import com.zqy.baserequest.request.Request;
import com.zqy.baseutil.code.impl.RSAEncrypt;
import com.zqy.gamemange.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RoundButton mTvTest;
    private AppCompatTextView mAtvContent;

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


        //  RSAEncrypt.genKeyPair();
        String s = RSAEncrypt.encryptPublicKey("123");
        String s1 = RSAEncrypt.decryptPrivateKey(s, RSAEncrypt.PRIVATE_KEY);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rbt_test:
                Request.getInstance().test(new JsonEntityCallback<DescAndCode>(DescAndCode.class) {
                    @Override
                    protected void onSuccess(DescAndCode descAndCode) {
                        String toJson = new Gson().toJson(descAndCode);
                        mAtvContent.setText(toJson);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mAtvContent.setText(response.getException().toString());
                    }
                });
                break;
            case R.id.atv_content:
                break;
        }
    }
}