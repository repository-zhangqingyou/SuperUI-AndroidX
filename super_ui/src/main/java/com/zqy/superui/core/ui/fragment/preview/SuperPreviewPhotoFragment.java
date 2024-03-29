package com.zqy.superui.core.ui.fragment.preview;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.imageview.photoview.PhotoViewAttacher;
import com.xuexiang.xui.widget.imageview.preview.MediaLoader;
import com.xuexiang.xui.widget.imageview.preview.loader.ISimpleTarget;
import com.xuexiang.xui.widget.imageview.preview.loader.OnVideoClickListener;
import com.xuexiang.xui.widget.imageview.preview.ui.PreviewActivity;
import com.xuexiang.xui.widget.imageview.preview.ui.VideoPlayerActivity;
import com.xuexiang.xui.widget.imageview.preview.view.SmoothImageView;
import com.xuexiang.xui.widget.progress.materialprogressbar.MaterialProgressBar;
import com.zqy.superui.R;
import com.zqy.superui.core.module.ImageViewInfo;
import com.zqy.superui.core.ui.activity.preview.SuperUIMultiPreviewActivity;


/**
 * 作者: zhangqingyou
 * 时间: 2021/3/11
 * 描述: 图片预览单个图片的fragment
 */
public class SuperPreviewPhotoFragment extends Fragment {
    private static final String GIF = ".gif";
    /**
     * 预览图片 类型
     */
    public static final String KEY_TRANS_PHOTO = "com.xuexiang.xui.widget.preview.KEY_TRANS_PHOTO";
    public static final String KEY_SING_FILING = "com.xuexiang.xui.widget.preview.KEY_SING_FILING";
    public static final String KEY_PREVIEW_ITEM = "com.xuexiang.xui.widget.preview.KEY_PREVIEW_ITEM";
    public static final String KEY_DRAG = "com.xuexiang.xui.widget.preview.KEY_DRAG";
    public static final String KEY_SENSITIVITY = "com.xuexiang.xui.widget.preview.KEY_SENSITIVITY";
    public static final String KEY_PROGRESS_COLOR = "com.xuexiang.xui.widget.preview.KEY_PROGRESS_COLOR";
    private ImageViewInfo mPreviewInfo;
    private boolean isTransPhoto = false;
    protected SmoothImageView mImageView;
    protected View mRootView;
    protected MaterialProgressBar mLoadingView;
    protected ISimpleTarget mISimpleTarget;
    private SimpleTarget<Drawable> simpleTarget;
    protected ImageView mBtnVideo;
    public static OnVideoClickListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.superui_fragment_image_photo, container, false);
    }//

    /**
     * 构造方法
     *
     * @param fragmentClass   预览fragment的类
     * @param item            图片预览接口
     * @param currentIndex    当前索引
     * @param isSingleFling   是否单图
     * @param isDrag          是否可拖拽
     * @param sensitivity     灵敏度
     * @param progressColorId 进度条的颜色
     * @return
     */
    public static SuperPreviewPhotoFragment newInstance(Class<? extends SuperPreviewPhotoFragment> fragmentClass,
                                                        ImageViewInfo item, boolean currentIndex,
                                                        boolean isSingleFling,
                                                        boolean isDrag,
                                                        float sensitivity,
                                                        int progressColorId) {
        SuperPreviewPhotoFragment fragment;
        try {
            fragment = fragmentClass.newInstance();
        } catch (Exception e) {
            fragment = new SuperPreviewPhotoFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PREVIEW_ITEM, item);
        bundle.putBoolean(KEY_TRANS_PHOTO, currentIndex);
        bundle.putBoolean(KEY_SING_FILING, isSingleFling);
        bundle.putBoolean(KEY_DRAG, isDrag);
        bundle.putFloat(KEY_SENSITIVITY, sensitivity);
        bundle.putInt(KEY_PROGRESS_COLOR, progressColorId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @CallSuper
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initArgs();
    }

    @CallSuper
    @Override
    public void onStop() {
        MediaLoader.get().onStop(this);
        super.onStop();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        release();
        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MediaLoader.get().clearMemory(getActivity());
        if (getActivity() != null && getActivity().isFinishing()) {
            listener = null;
        }
    }

    public void release() {
        mISimpleTarget = null;
        if (mImageView != null) {
            mImageView.setImageBitmap(null);
            mImageView.setOnViewTapListener(null);
            mImageView.setOnPhotoTapListener(null);
            mImageView.setAlphaChangeListener(null);
            mImageView.setTransformOutListener(null);
            mImageView.transformIn(null);
            mImageView.transformOut(null);
            mImageView.setOnLongClickListener(null);
            mBtnVideo.setOnClickListener(null);
            mImageView = null;
            mRootView = null;
            isTransPhoto = false;
        }
    }

    /**
     * 初始化控件
     */
    private void initView(View view) {
        mLoadingView = view.findViewById(R.id.loading);
        mImageView = view.findViewById(R.id.photoView);
        mBtnVideo = view.findViewById(R.id.btnVideo);
        mRootView = view.findViewById(R.id.rootView);
        mRootView.setDrawingCacheEnabled(false);
        mImageView.setDrawingCacheEnabled(false);
        mBtnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String video = mPreviewInfo.getUrlVideo();
                if (video != null && !video.isEmpty()) {
                    if (listener != null) {
                        listener.onPlayerVideo(video);
                    } else {
                        VideoPlayerActivity.start(SuperPreviewPhotoFragment.this, video);
                    }
                }

            }
        });

        mISimpleTarget = new ISimpleTarget() {

            @Override
            public void onResourceReady() {
                mLoadingView.setVisibility(View.GONE);
                String video = mPreviewInfo.getUrlVideo();
                if (video != null && !video.isEmpty()) {
                    mBtnVideo.setVisibility(View.VISIBLE);
                    ViewCompat.animate(mBtnVideo).alpha(1).setDuration(1000).start();
                } else {
                    mBtnVideo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadFailed(Drawable errorDrawable) {
                mLoadingView.setVisibility(View.GONE);
                mBtnVideo.setVisibility(View.GONE);
                if (errorDrawable != null) {
                    mImageView.setImageDrawable(errorDrawable);
                }
            }
        };

        simpleTarget = new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                mImageView.setImageDrawable(resource);
                mLoadingView.setVisibility(View.GONE);
                String video = mPreviewInfo.getUrlVideo();
                if (video != null && !video.isEmpty()) {
                    mBtnVideo.setVisibility(View.VISIBLE);
                    ViewCompat.animate(mBtnVideo).alpha(1).setDuration(1000).start();
                } else {
                    mBtnVideo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                mLoadingView.setVisibility(View.GONE);
                mBtnVideo.setVisibility(View.GONE);
                if (errorDrawable != null) {
                    mImageView.setImageDrawable(errorDrawable);
                }
            }
        };
    }

    /**
     * 初始化参数
     */
    private void initArgs() {
        Bundle bundle = getArguments();
        boolean isSingleFling = true;
        // 非动画进入的Fragment，默认背景为黑色
        if (bundle != null) {
            int colorId = bundle.getInt(KEY_PROGRESS_COLOR, R.color.xui_config_color_main_theme);
            mLoadingView.setSupportIndeterminateTintList(ResUtils.getColors(colorId));
            isSingleFling = bundle.getBoolean(KEY_SING_FILING);
            //地址
            mPreviewInfo = bundle.getParcelable(KEY_PREVIEW_ITEM);
            //位置
            assert mPreviewInfo != null;
            mImageView.setDrag(bundle.getBoolean(KEY_DRAG), bundle.getFloat(KEY_SENSITIVITY));
            mImageView.setThumbRect(mPreviewInfo.getBounds());
            mRootView.setTag(mPreviewInfo.getUrlImg());
            //是否展示动画
            isTransPhoto = bundle.getBoolean(KEY_TRANS_PHOTO, false);

            ImageViewInfo mPreviewInfo = (ImageViewInfo) this.mPreviewInfo;
            Bitmap bitmap = mPreviewInfo.getBitmap();
            if (bitmap != null) {
                Glide.with(getContext()).load(bitmap).into(simpleTarget);
            } else {
                Glide.with(getContext()).load(mPreviewInfo.getUrlImg()).into(simpleTarget);
            }


//            } else {
//                if (!TextUtils.isEmpty(mPreviewInfo.getUrl())) {
//                    if (mPreviewInfo.getUrl().toLowerCase().contains(GIF)) {
//                        mImageView.setZoomable(false);
//                        //加载图
//                        MediaLoader.get().displayGifImage(this, mPreviewInfo.getUrl(), mImageView, mISimpleTarget);
//                    } else {
//                        //加载图
//                        MediaLoader.get().displayImage(this, mPreviewInfo.getUrl(), mImageView, mISimpleTarget);
//                    }
//                }
//
//            }


        }
        if (!isTransPhoto) {
            mRootView.setBackgroundColor(Color.BLACK);
        } else {
            mImageView.setMinimumScale(0.7f);
        }
        if (isSingleFling) {
            mImageView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float x, float y) {
                    if (mImageView.checkMinScale()) {
                        transformOut();
                    }
                }
            });
        } else {
            mImageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    if (mImageView.checkMinScale()) {
                        transformOut();
                    }
                }

                @Override
                public void onOutsidePhotoTap() {

                }
            });
        }
        mImageView.setAlphaChangeListener(new SmoothImageView.OnAlphaChangeListener() {
            @Override
            public void onAlphaChange(int alpha) {
                if (alpha == 255) {
                    String video = mPreviewInfo.getUrlVideo();
                    if (video != null && !video.isEmpty()) {
                        mBtnVideo.setVisibility(View.VISIBLE);
                    } else {
                        mBtnVideo.setVisibility(View.GONE);
                    }
                } else {
                    mBtnVideo.setVisibility(View.GONE);
                }
                mRootView.setBackgroundColor(getColorWithAlpha(alpha / 255f, Color.BLACK));

            }
        });
        mImageView.setTransformOutListener(new SmoothImageView.OnTransformOutListener() {
            @Override
            public void onTransformOut() {
                transformOut();
            }
        });
    }

    /**
     * 退出预览的动画
     */
    private void transformOut() {
        if (getActivity() instanceof PreviewActivity) {
            PreviewActivity activity = ((PreviewActivity) getActivity());
            if (activity != null) {
                activity.transformOut();
            }
        } else if (getActivity() instanceof SuperUIMultiPreviewActivity) {
            SuperUIMultiPreviewActivity activity = ((SuperUIMultiPreviewActivity) getActivity());
            if (activity != null) {
                activity.transformOut();
            }
        }


    }

    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

    public void transformIn() {
        mImageView.transformIn(new SmoothImageView.onTransformListener() {
            @Override
            public void onTransformCompleted(SmoothImageView.Status status) {
                mRootView.setBackgroundColor(Color.BLACK);
            }
        });
    }

    public void transformOut(SmoothImageView.onTransformListener listener) {
        mImageView.transformOut(listener);
    }

    public void resetMatrix() {
        if (mImageView != null) {
            mImageView.resetMatrix();
        }
    }

    public void changeBg(int color) {
        ViewCompat.animate(mBtnVideo).alpha(0).setDuration(500).start();
        mRootView.setBackgroundColor(color);
    }

    public ImageViewInfo getImageViewInfo() {
        return mPreviewInfo;
    }
}
