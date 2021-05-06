package com.zqy.suidemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SizeUtils;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView2;
import com.zqy.suidemo.R;
import com.zqy.superui.core.enums.Gradient;
import com.zqy.superui.core.other.drawable.SuperStateListDrawable;
import com.zqy.superui.core.widget.framelayout.SuperFrameLayout;
import com.zqy.superui.core.widget.popup.tips.LoadPopup;
import com.zqy.superui.core.widget.textview.SuperTextView;

import java.util.ArrayList;
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

//        String json = "{\"cmd\":\"get_phone_info\",\"param\":{\"phone_name\":\"rockchip:KC2\",\"brand\":\"Android\",\"os_version\":\"Android7.1.2\",\"rom_version\":\"rk3399-userdebug 7.1.2 NHG47K eng.robert.20200908.200616 test-keys eng.robert.20200908.200616\",\"imei\":\"\",\"imsi\":\"\",\"resolution\":\"1280x800\",\"mac\":\"b0:02:47:94:24:eb\",\"ip\":\"192.168.1.116\",\n" +
//                "\"cpu\":\"\",\"ram\":0,\"root\":1,\"app_count\":3,\"time\":1620271406}}{\"cmd\":\"get_app_info\",\"param\":{\"name\":\"Android行为测试\",\"package\":\"zctt.android.testapi\",\n" +
//                "\"version\":\"1.0\",\"apk_md5\":\"5D4AB006182159E171D06BB151853E02\",\"icon_path\":\"/sdcard/.ppp/zctt.android.testapi.p\",\"apk_path\":\"/data/app/zctt.android.testapi-1/base.apk\",\"apptype\":\"1\",\"permissions\":[\"android.permission.READ_PRIVILEGED_PHONE_STATE\",\"android.permission.BLUETOOTH_ADMIN\",\"android.permission.WRITE_SETTINGS\",\"android.permission.CALL_PHONE\",\"android.permission.INTERNET\",\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.READ_EXTERNAL_STORAGE\",\"android.permission.READ_PHONE_STATE\",\"android.permission.SEND_SMS\",\"android.permission.READ_CONTACTS\",\"android.permission.READ_CALL_LOG\",\"android.permission.READ_SMS\",\"android.permission.RECEIVE_SMS\",\"com.android.browser.permission.READ_HISTORY_BOOKMARKS\",\"android.permission.ACCESS_WIFI_STATE\",\"android.permission.CHANGE_WIFI_STATE\",\"android.permission.ACCESS_COARSE_LOCATION\",\"android.permission.ACCESS_FINE_LOCATION\",\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.CHANGE_NETWORK_STATE\",\"android.permission.RECORD_AUDIO\",\"android.permission.CAMERA\",\"android.permission.BLUETOOTH\",\"android.permission.READ_CALENDAR\",\"android.permission.WRITE_CALENDAR\",\"android.permission.REQUEST_INSTALL_PACKAGES\",\"android.permission.ACCESS_LOCATION_EXTRA_COMMANDS\",\"android.permission.PROCESS_OUTGOING_CALLS\",\"android.permission.ACCESS_MOCK_LOCATION\",\"android.permission.WAKE_LOCK\"],\"percent\":33}}{\"cmd\":\"get_app_info\",\"param\":{\"name\":\"Antivirus-pad\",\"package\":\"com.example.antivirus_pad\",\"version\":\"1.0\",\"apk_md5\":\"E42CAE60B99C1F55041E1800E751EFDD\",\"icon_path\":\"/sdcard/.ppp/com.example.antivirus_pad.p\",\"apk_path\":\"/data/app/com.example.antivirus_pad-1/base.apk\",\"apptype\":\"1\",\"permissions\":[\"android.permission.WRITE_EXTERNAL_STORAGE\",\"android.permission.READ_EXTERNAL_STORAGE\"],\"percent\":66}}{\"cmd\":\"get_app_info\",\"param\":{\"name\":\"MobileGuard\",\"package\":\"com.antivirus.pad\",\"version\":\"3.0\",\"apk_md5\":\"ABD29C52790469604BD70C5E5FBFF463\",\"icon_path\":\"/sdcard/.ppp/com.antivirus.pad.p\",\"apk_path\":\"/data/app/com.antivirus.pad-1/base.apk\",\"apptype\":\"1\",\"permissions\":[\"android.permission.RECEIVE_BOOT_COMPLETED\",\"android.permission.ACCESS_WIFI_STATE\",\"android.permission.ACCESS_NETWORK_STATE\",\"android.permission.INTERNET\",\"android.permission.CHANGE_NETWORK_STATE\",\"android.permission.REORDER_TASKS\",\"android.permission.WAKE_LOCK\",\"android.permission.CHANGE_WIFI_STATE\",\"android.permission.READ_PHONE_STATE\",\"ANDROID.PERMISSION.MOUNT_UNMOUNT_FILESYSTEMS\",\"android.permission.READ_EXTERNAL_STORAGE\",\"android.permission.SYSTEM_ALERT_WINDOW\",\"android.permission.GET_TASKS\",\"android.permission.WRITE_EXTERNAL_STORAGE\"],\"percent\":100}}";
//
//        StringBuilder stringBuilder = new StringBuilder();
//        if (json.endsWith("}}")) json = json.substring(0, json.length() - 2);
//        String[] split = json.split("\\}\\}");
//        for (int i = 0; i < split.length; i++) {
//            String s = split[i];
//            if (i < split.length - 1) {
//                if (i == 0) {
//                    stringBuilder.append("[").append(s).append("}},");
//                } else {
//                    stringBuilder.append(s).append("}},");
//                }
//            } else {
//                stringBuilder.append(s).append("}}]");
//            }
//
//        }
//        Log.d("json", stringBuilder.toString());

//        List<KV> kvs = analyze(json, "time");
//        for (KV kv : kvs) {
//            Log.d("解析", "字段：" + kv.getField() + "---值：" + kv.getValue());
//        }


    }


    class KV {
        private String field;
        private String value;

        public KV(String field, String value) {
            this.field = field;
            this.value = value;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * @param json
     * @param field 字段名
     * @return
     */
    private List<KV> analyze(String json, String field) {
        List<KV> kvs = new ArrayList<>();
        String[] split = json.split(",");
        for (String s : split) {
            String startChar = "\"" + field + "\":";
            if (s.contains(startChar)) {
                int indexOfStart = s.indexOf(startChar);
                String substring = s.substring(indexOfStart, s.length() - 1);
                String replace = substring.replace(startChar, "").replace("\"", "");
                if (replace.contains(",")) {
                    kvs.add(new KV(field, replace.substring(0, replace.indexOf(","))));
                } else if (replace.contains("}")) {
                    kvs.add(new KV(field, replace.substring(0, replace.indexOf("}"))));
                } else {
                    kvs.add(new KV(field, replace));
                }
            }
        }
        return kvs;
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