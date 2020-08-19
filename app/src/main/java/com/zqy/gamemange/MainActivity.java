package com.zqy.gamemange;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.gson.Gson;
import com.hjy.baserequest.bean.DescAndCode;
import com.hjy.baserequest.request.JsonEntityCallback;
import com.hjy.baserequest.request.Request;
import com.hjy.baseutil.code.impl.RSAEncrypt;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton mTvTest;
    private AppCompatTextView mAtvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        mTvTest = (AppCompatButton) findViewById(R.id.tv_test);
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
            case R.id.tv_test:
                Request.getInstance().test(new JsonEntityCallback<DescAndCode>(DescAndCode.class) {
                    @Override
                    protected void onSuccess(DescAndCode descAndCode) {
                        String toJson = new Gson().toJson(descAndCode);
                        mAtvContent.setText(toJson);
                    }
                });
                break;
            case R.id.atv_content:
                break;
        }
    }
}