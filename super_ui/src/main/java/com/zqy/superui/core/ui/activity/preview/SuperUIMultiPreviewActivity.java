package com.zqy.superui.core.ui.activity.preview;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xuexiang.xui.utils.CollectionUtils;
import com.xuexiang.xui.widget.imageview.preview.MediaLoader;
import com.xuexiang.xui.widget.imageview.preview.enitity.IPreviewInfo;
import com.xuexiang.xui.widget.imageview.preview.view.BezierBannerView;
import com.xuexiang.xui.widget.imageview.preview.view.PhotoViewPager;
import com.xuexiang.xui.widget.imageview.preview.view.SmoothImageView;
import com.zqy.superui.R;
import com.zqy.superui.core.ui.activity.simple.SimpleToolbarActivity;
import com.zqy.superui.core.ui.fragment.preview.SuperPhotoFragment;

import java.util.ArrayList;
import java.util.List;

import static com.xuexiang.xui.widget.imageview.preview.ui.BasePhotoFragment.KEY_DRAG;
import static com.xuexiang.xui.widget.imageview.preview.ui.BasePhotoFragment.KEY_PROGRESS_COLOR;
import static com.xuexiang.xui.widget.imageview.preview.ui.BasePhotoFragment.KEY_SENSITIVITY;
import static com.xuexiang.xui.widget.imageview.preview.ui.BasePhotoFragment.KEY_SING_FILING;

/**
 * 作者: zhangqingyou
 * 时间: 2021/5/19
 * 描述: 多图预览
 *       SuperUIPreviewBuilder.from(this)
 *                         .setImg(imageViewInfo)
 *                         .setSingleFling(true)//设置超出内容点击退出（黑色区域）
 *                         .setCurrentIndex(0)//设置默认索引
 *                         .setProgressColor(R.color.colorAccent)
 *                         .setType(PreviewBuilder.IndicatorType.Dot)//指示器类型
 *                         .setSingleShowType(false)// 是否设置为一张图片时 显示指示器
 *                         .start();
 */
public class SuperUIMultiPreviewActivity extends SimpleToolbarActivity {


    public final static String IMG_URL_LIST = "";//图片地址集
    public final static String TITLE_KEY = "标题";//

    @Override
    public Object getContentLayout() {
        return R.layout.superui_multi_preview_activity;
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
        getToolbar().setTitle("");
        getTbText().setText("预览");
        initArgs();
    }


    @Override
    public void initData() {
        super.initData();

        mViewPager = findViewById(R.id.viewPager);
        //viewPager的适配器
        SuperUIMultiPreviewActivity.PhotoPagerAdapter adapter = new SuperUIMultiPreviewActivity.PhotoPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(mCurrentIndex);
        mViewPager.setOffscreenPageLimit(3);
        mBezierBannerView = findViewById(R.id.bezierBannerView);
        mTvIndex = findViewById(R.id.tv_index);
        if (mType == SuperUIPreviewBuilder.IndicatorType.Dot) {
            mBezierBannerView.setVisibility(View.VISIBLE);
            mBezierBannerView.attachToViewpager(mViewPager);
        } else {
            mTvIndex.setVisibility(View.VISIBLE);
            mTvIndex.setText(getString(R.string.xui_preview_count_string, (mCurrentIndex + 1), getImgSize()));
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    //当被选中的时候设置小圆点和当前位置
                    if (mTvIndex != null) {
                        mTvIndex.setText(getString(R.string.xui_preview_count_string, (position + 1), getImgSize()));
                    }
                    mCurrentIndex = position;
                    mViewPager.setCurrentItem(mCurrentIndex, true);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
        if (fragments.size() == 1) {
            if (!mIsShow) {
                mBezierBannerView.setVisibility(View.GONE);
                mTvIndex.setVisibility(View.GONE);
            }
        }
        mViewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mViewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                SuperPhotoFragment fragment = CollectionUtils.getListItem(fragments, mCurrentIndex);
                if (fragment != null) {
                    fragment.transformIn();
                }
            }
        });
    }

    @Override
    public void listener() {
        super.listener();

    }

    @Override
    public int getToolbarMenu() {
        return 0;
    }


    public static final String KEY_IMAGE_PATHS = "com.xuexiang.xui.widget.preview.KEY_IMAGE_PATHS";
    public static final String KEY_POSITION = "com.xuexiang.xui.widget.preview.KEY_POSITION";
    public static final String KEY_TYPE = "com.xuexiang.xui.widget.preview.KEY_TYPE";
    public static final String KEY_IS_SHOW = "com.xuexiang.xui.widget.preview.KEY_IS_SHOW";
    public static final String KEY_DURATION = "com.xuexiang.xui.widget.preview.KEY_DURATION";
    public static final String KEY_IS_FULLSCREEN = "com.xuexiang.xui.widget.preview.KEY_IS_FULLSCREEN";
    public static final String KEY_CLASSNAME = "com.xuexiang.xui.widget.preview.KEY_CLASSNAME";

    private boolean mIsTransformOut = false;
    /*** 图片的地址***/
    private List<IPreviewInfo> mImgUrls;
    /*** 当前图片的位置 ***/
    private int mCurrentIndex;
    /*** 图片的展示的Fragment***/
    private List<SuperPhotoFragment> fragments = new ArrayList<>();
    /*** 展示图片的viewPager ***/
    private PhotoViewPager mViewPager;
    /*** 显示图片数**/
    private TextView mTvIndex;
    /***指示器控件**/
    private BezierBannerView mBezierBannerView;
    /***指示器类型枚举***/
    private SuperUIPreviewBuilder.IndicatorType mType;
    /***默认显示***/
    private boolean mIsShow = true;


    @Override
    public void onDestroy() {
        MediaLoader.get().clearMemory(this);
        if (mViewPager != null) {
            mViewPager.setAdapter(null);
            mViewPager.clearOnPageChangeListeners();
            mViewPager.removeAllViews();
            mViewPager = null;
        }
        if (fragments != null) {
            fragments.clear();
            fragments = null;
        }
        if (mImgUrls != null) {
            mImgUrls.clear();
            mImgUrls = null;
        }
        super.onDestroy();
    }

    /**
     * 初始化参数
     */
    private void initArgs() {
        mImgUrls = getIntent().getParcelableArrayListExtra(KEY_IMAGE_PATHS);
        mCurrentIndex = getIntent().getIntExtra(KEY_POSITION, -1);
        mType = (SuperUIPreviewBuilder.IndicatorType) getIntent().getSerializableExtra(KEY_TYPE);
        mIsShow = getIntent().getBooleanExtra(KEY_IS_SHOW, true);
        int duration = getIntent().getIntExtra(KEY_DURATION, 300);
        boolean isFullscreen = getIntent().getBooleanExtra(KEY_IS_FULLSCREEN, false);
        if (isFullscreen) {
            setTheme(android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        }
        try {
            SmoothImageView.setDuration(duration);
            Class<? extends SuperPhotoFragment> clazz;
            clazz = (Class<? extends SuperPhotoFragment>) getIntent().getSerializableExtra(KEY_CLASSNAME);
            initFragment(mImgUrls, mCurrentIndex, clazz);
        } catch (Exception e) {
            initFragment(mImgUrls, mCurrentIndex, SuperPhotoFragment.class);
        }

    }

    /**
     * 初始化
     *
     * @param imgUrls      集合
     * @param currentIndex 选中索引
     * @param className    显示Fragment
     **/
    protected void initFragment(List<IPreviewInfo> imgUrls, int currentIndex, Class<? extends SuperPhotoFragment> className) {
        if (imgUrls != null) {
            int size = imgUrls.size();
            for (int i = 0; i < size; i++) {
                fragments.add(SuperPhotoFragment.
                        newInstance(className, imgUrls.get(i),
                                currentIndex == i,
                                getIntent().getBooleanExtra(KEY_SING_FILING, false),
                                getIntent().getBooleanExtra(KEY_DRAG, false),
                                getIntent().getFloatExtra(KEY_SENSITIVITY, 0.5f),
                                getIntent().getIntExtra(KEY_PROGRESS_COLOR, R.color.xui_config_color_main_theme))
                );
            }
        } else {
            finish();
        }
    }


    private int getImgSize() {
        return CollectionUtils.getSize(mImgUrls);
    }

    /***退出预览的动画***/
    public void transformOut() {
        if (mIsTransformOut) {
            return;
        }
        mIsTransformOut = true;
        int currentItem = mViewPager.getCurrentItem();
        if (currentItem < getImgSize()) {
            SuperPhotoFragment fragment = fragments.get(currentItem);
            if (mTvIndex != null) {
                mTvIndex.setVisibility(View.GONE);
            } else {
                mBezierBannerView.setVisibility(View.GONE);
            }
            fragment.changeBg(Color.TRANSPARENT);
            fragment.transformOut(new SmoothImageView.onTransformListener() {
                @Override
                public void onTransformCompleted(SmoothImageView.Status status) {
                    exit();
                }
            });
        } else {
            exit();
        }
    }

    @Override
    public void finish() {
        SuperPhotoFragment.listener = null;
        super.finish();
    }

    /***
     * 得到PhotoFragment集合
     * @return List
     * **/
    public List<SuperPhotoFragment> getFragments() {
        return fragments;
    }

    /**
     * 关闭页面
     */
    private void exit() {
        finish();
        overridePendingTransition(0, 0);
    }

    /***
     * 得到PhotoViewPager
     * @return PhotoViewPager
     * **/
    public PhotoViewPager getViewPager() {
        return mViewPager;
    }


    @Override
    public void onBackPressed() {
        transformOut();
    }

    /**
     * pager的适配器
     */
    private class PhotoPagerAdapter extends FragmentPagerAdapter {

        PhotoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }
    }


}
