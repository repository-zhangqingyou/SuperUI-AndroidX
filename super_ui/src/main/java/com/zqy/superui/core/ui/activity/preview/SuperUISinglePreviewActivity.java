package com.zqy.superui.core.ui.activity.preview;

import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.zqy.superui.R;
import com.zqy.superui.core.module.ImageViewInfo;
import com.zqy.superui.core.ui.activity.simple.SimpleToolbarActivity;
import com.zqy.superui.core.ui.fragment.preview.SuperPhotoFragment;

/**
 * 作者: zhangqingyou
 * 时间: 2021/5/19
 * 描述: 单图预览
 * String img = "https://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fimg.jj20.com%252Fup%252Fallimg%252Ftp05%252F1910020U602A43-0-lp.jpg%26refer%3Dhttp%253A%252F%252Fimg.jj20.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1640223918%26t%3Dd80c4abeb270fc8999d3428781c82049&thumburl=https%3A%2F%2Fimg0.baidu.com%2Fit%2Fu%3D638855217%2C3271693300%26fm%3D26%26fmt%3Dauto";
 * ImageViewInfo imageViewInfo = new ImageViewInfo(img, mIvPreview);
 * intent = new Intent(this, SuperUISinglePreviewActivity.class);
 * intent.putExtra(SuperUISinglePreviewActivity.TITLE_KEY, "单图预览");
 * intent.putExtra(SuperUISinglePreviewActivity.IMAGE_VIEW_INFO_KEY, imageViewInfo);
 * startActivity(intent);
 */
public class SuperUISinglePreviewActivity extends SimpleToolbarActivity {

    private LinearLayout mLlContainer;

    public final static String IMAGE_VIEW_INFO_KEY = "ImageViewInfo";//图片
    public final static String TITLE_KEY = "标题";//


    @Override
    public Object getContentLayout() {
        return R.layout.superui_preview_activity;
    }

    @Override
    public boolean onStatusBarVisibility() {
        return true;
    }

    @Override
    public boolean onTransparentStatusBar() {
        return true;
    }

    @Override
    public boolean onStatusBarLightMode() {
        return true;
    }

    @Override
    public void initView() {
        super.initView();
        mLlContainer = findViewById(R.id.ll_ImgContainer);
        getToolbar().setTitle("");

        String title = getIntent().getStringExtra(TITLE_KEY);
        if (!TextUtils.isEmpty(title)) getTbText().setText(title);
        else
            getTbText().setText("预览");
    }


    @Override
    public void initData() {
        super.initData();
        /*** 图片的地址***/
        Parcelable parcelableExtra = getIntent().getParcelableExtra(IMAGE_VIEW_INFO_KEY);
        if (parcelableExtra instanceof ImageViewInfo) {
            SuperPhotoFragment superPhotoFragment = SuperPhotoFragment.
                    newInstance(SuperPhotoFragment.class, (ImageViewInfo) parcelableExtra,
                            false,//单图没有图片脚标
                            true,//是否单张
                            true,//   设置图片拖拽返回 @param isDrag true  可以 false 默认 true(关闭拖拽返回)   @param sensitivity 灵敏度
                            0.5f,
                            R.color.colorAccent);
            showFragment(R.id.ll_ImgContainer, superPhotoFragment);
        }

    }

    @Override
    public void listener() {
        super.listener();

    }

    @Override
    public int getToolbarMenu() {
        return 0;
    }


}
