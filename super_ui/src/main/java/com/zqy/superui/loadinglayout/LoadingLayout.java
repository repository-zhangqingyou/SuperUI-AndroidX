package com.zqy.superui.loadinglayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SizeUtils;
import com.zqy.superui.R;


/**
 * 作者: zhangqingyou
 * 时间: 2020/11/15 18:19
 * 描述: 继承FrameLayout，在xml渲染完成后，加上加载中、无网络、无数据、出错四个页面，根据需要控制显示哪一层
 */
public class LoadingLayout extends FrameLayout {

    public final static int Success = 0;
    public final static int Empty = 1;
    public final static int Error = 2;
    public final static int No_Network = 3;
    public final static int Loading = 4;
    private int state;

    private Context mContext;
    private View loadingPage;
    private View errorPage;
    private View emptyPage;
    private View networkPage;
    private View defineLoadingPage;

    private ImageView errorImg;
    private ImageView emptyImg;
    private ImageView networkImg;

    private TextView errorText;
    private TextView emptyText;
    private TextView networkText;

    private TextView errorReloadBtn;
    private TextView networkReloadBtn;

    private View contentView;
    private OnReloadListener listener;
    private boolean isFirstVisible; //是否一开始显示contentview，默认不显示

    //配置
    private static Config mConfig = new Config();
    private static String emptyStr = "暂无数据";
    private static String errorStr = "加载失败，请稍后重试···";
    private static String netwrokStr = "无网络连接，请检查网络···";
    private static String reloadBtnStr = "点击重试";
    private static int emptyImgId = R.mipmap.empty;
    private static int errorImgId = R.mipmap.error;
    private static int networkImgId = R.mipmap.no_network;
    private static int reloadBtnId = R.drawable.selector_btn_back_gray;
    private static int tipTextSize = 14;
    private static int buttonTextSize = 14;
    private static int tipTextColor = R.color.base_text_color_light;
    private static int buttonTextColor = R.color.base_text_color_light;
    private static int buttonWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static int buttonHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static int errorImgWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static int errorImgHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static int emptyImgWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static int emptyImgHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static int networkImgWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static int networkImgHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static int marginTop_dp = 10;
    private static int loadingLayoutId = R.layout.widget_loading_page;
    private static int backgroundColor = R.color.base_loading_background;
    private static boolean isReloadButtonVisibility;

    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadingLayout);
        isFirstVisible = a.getBoolean(R.styleable.LoadingLayout_isFirstVisible, false);
        a.recycle();
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public LoadingLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalStateException("LoadingLayout can host only one direct child");
        }
        contentView = this.getChildAt(0);
        if (!isFirstVisible) {
            contentView.setVisibility(View.GONE);
        }
        build();
    }

    private void build() {

        loadingPage = LayoutInflater.from(mContext).inflate(loadingLayoutId, null);
        errorPage = LayoutInflater.from(mContext).inflate(R.layout.widget_error_page, null);
        emptyPage = LayoutInflater.from(mContext).inflate(R.layout.widget_empty_page, null);
        networkPage = LayoutInflater.from(mContext).inflate(R.layout.widget_nonetwork_page, null);
        defineLoadingPage = null;

        setStatusBackgroundColor(ContextCompat.getColor(mContext, backgroundColor));

        errorText = errorPage.findViewById(R.id.error_text);
        emptyText = emptyPage.findViewById(R.id.empty_text);
        networkText = networkPage.findViewById(R.id.no_network_text);

        errorImg = errorPage.findViewById(R.id.error_img);
        emptyImg = emptyPage.findViewById(R.id.empty_img);
        networkImg = networkPage.findViewById(R.id.no_network_img);

        errorReloadBtn = errorPage.findViewById(R.id.error_reload_btn);
        networkReloadBtn = networkPage.findViewById(R.id.no_network_reload_btn);

        errorReloadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onReload(v);
                }
            }
        });
        networkReloadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.onReload(v);
                }
            }
        });

        errorText.setText(errorStr);
        emptyText.setText(emptyStr);
        networkText.setText(netwrokStr);

        errorText.setTextSize(tipTextSize);
        emptyText.setTextSize(tipTextSize);
        networkText.setTextSize(tipTextSize);

        errorText.setTextColor(ContextCompat.getColor(mContext, tipTextColor));
        emptyText.setTextColor(ContextCompat.getColor(mContext, tipTextColor));
        networkText.setTextColor(ContextCompat.getColor(mContext, tipTextColor));

        errorImg.setImageResource(errorImgId);
        emptyImg.setImageResource(emptyImgId);
        networkImg.setImageResource(networkImgId);


        errorReloadBtn.setBackgroundResource(reloadBtnId);
        networkReloadBtn.setBackgroundResource(reloadBtnId);

        errorReloadBtn.setText(reloadBtnStr);
        networkReloadBtn.setText(reloadBtnStr);

        errorReloadBtn.setTextSize(buttonTextSize);
        networkReloadBtn.setTextSize(buttonTextSize);

        errorReloadBtn.setTextColor(ContextCompat.getColor(mContext, buttonTextColor));
        networkReloadBtn.setTextColor(ContextCompat.getColor(mContext, buttonTextColor));

        if (isReloadButtonVisibility) {
            errorReloadBtn.setVisibility(VISIBLE);
            networkReloadBtn.setVisibility(VISIBLE);
        } else {
            errorReloadBtn.setVisibility(GONE);
            networkReloadBtn.setVisibility(GONE);
        }


        setReloadButtonWH(buttonWidth, buttonHeight);
        setErrorImageWH(errorImgWidth, errorImgHeight);
        setEmptyImageWH(emptyImgWidth, emptyImgHeight);
        setNoNetworkImageWH(networkImgWidth, networkImgHeight);
        setTextMarginTop(marginTop_dp);
        this.addView(networkPage);
        this.addView(emptyPage);
        this.addView(errorPage);
        this.addView(loadingPage);
    }

    public void setStatus(@Flavour int status) {

        this.state = status;

        switch (status) {
            case Success:

                contentView.setVisibility(View.VISIBLE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {

                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            case Loading:

                contentView.setVisibility(View.GONE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {
                    defineLoadingPage.setVisibility(View.VISIBLE);
                } else {
                    loadingPage.setVisibility(View.VISIBLE);
                }
                break;

            case Empty:

                contentView.setVisibility(View.GONE);
                emptyPage.setVisibility(View.VISIBLE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {
                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            case Error:

                contentView.setVisibility(View.GONE);
                loadingPage.setVisibility(View.GONE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.VISIBLE);
                networkPage.setVisibility(View.GONE);
                if (defineLoadingPage != null) {
                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            case No_Network:

                contentView.setVisibility(View.GONE);
                loadingPage.setVisibility(View.GONE);
                emptyPage.setVisibility(View.GONE);
                errorPage.setVisibility(View.GONE);
                networkPage.setVisibility(View.VISIBLE);
                if (defineLoadingPage != null) {

                    defineLoadingPage.setVisibility(View.GONE);
                } else {
                    loadingPage.setVisibility(View.GONE);
                }
                break;

            default:
                break;
        }

    }


    /**
     * 返回当前状态{Success, Empty, Error, No_Network, Loading}
     *
     * @return
     */
    public int getStatus() {

        return state;
    }

    /**
     * 设置Empty状态提示文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public LoadingLayout setEmptyText(String text) {

        emptyText.setText(text);
        return this;
    }

    /**
     * 设置Error状态提示文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public LoadingLayout setErrorText(String text) {

        errorText.setText(text);
        return this;
    }

    /**
     * 设置No_Network状态提示文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public LoadingLayout setNoNetworkText(String text) {

        networkText.setText(text);
        return this;
    }

    /**
     * 设置Empty状态显示图片，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setEmptyImage(@DrawableRes int id) {


        emptyImg.setImageResource(id);
        return this;
    }

    /**
     * 设置Error状态显示图片，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setErrorImage(@DrawableRes int id) {

        errorImg.setImageResource(id);
        return this;
    }

    /**
     * 设置No_Network状态显示图片，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setNoNetworkImage(@DrawableRes int id) {

        networkImg.setImageResource(id);
        return this;
    }

    /**
     * 设置Empty状态提示文本的字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public LoadingLayout setEmptyTextSize(int sp) {

        emptyText.setTextSize(sp);
        return this;
    }

    /**
     * 设置Empty状态提示文本的字体颜色
     *
     * @param color
     * @return
     */
    public LoadingLayout setEmptyTextColor(@ColorInt int color) {
        emptyText.setTextColor(color);
        return this;
    }

    /**
     * 设置Error状态提示文本的字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public LoadingLayout setErrorTextSize(int sp) {

        errorText.setTextSize(sp);
        return this;
    }

    /**
     * 设置Error状态提示文本的字体颜色
     *
     * @param color
     * @return
     */
    public LoadingLayout setErrorTextColor(@ColorInt int color) {
        errorText.setTextColor(color);
        return this;
    }

    /**
     * 设置No_Network状态提示文本的字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public LoadingLayout setNoNetworkTextSize(int sp) {

        networkText.setTextSize(sp);
        return this;
    }


    /**
     * 设置No_Network状态提示文本的字体颜色
     *
     * @param color
     * @return
     */
    public LoadingLayout setNoNetworkTextColor(@ColorInt int color) {
        networkText.setTextColor(color);
        return this;
    }

    /**
     * 设置Empty状态图片的显示与否，仅对当前所在的地方有效
     *
     * @param bool
     * @return
     */
    public LoadingLayout setEmptyImageVisible(boolean bool) {

        if (bool) {
            emptyImg.setVisibility(View.VISIBLE);
        } else {
            emptyImg.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置Error状态图片的显示与否，仅对当前所在的地方有效
     *
     * @param bool
     * @return
     */
    public LoadingLayout setErrorImageVisible(boolean bool) {

        if (bool) {
            errorImg.setVisibility(View.VISIBLE);
        } else {
            errorImg.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置No_Network状态图片的显示与否，仅对当前所在的地方有效
     *
     * @param bool
     * @return
     */
    public LoadingLayout setNoNetworkImageVisible(boolean bool) {

        if (bool) {
            networkImg.setVisibility(View.VISIBLE);
        } else {
            networkImg.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置ReloadButton的文本，仅对当前所在的地方有效
     *
     * @param text
     * @return
     */
    public LoadingLayout setReloadButtonText(@NonNull String text) {

        errorReloadBtn.setText(text);
        networkReloadBtn.setText(text);
        return this;
    }

    /**
     * 设置ReloadButton的文本字体大小，仅对当前所在的地方有效
     *
     * @param sp
     * @return
     */
    public LoadingLayout setReloadButtonTextSize(int sp) {

        errorReloadBtn.setTextSize(sp);
        networkReloadBtn.setTextSize(sp);
        return this;
    }

    /**
     * 设置ReloadButton的文本颜色，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setReloadButtonTextColor(@ColorRes int id) {

        errorReloadBtn.setTextColor(ContextCompat.getColor(mContext, id));
        networkReloadBtn.setTextSize(ContextCompat.getColor(mContext, id));
        return this;
    }

    /**
     * 设置ReloadButton的背景，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setReloadButtonBackgroundResource(@DrawableRes int id) {

        errorReloadBtn.setBackgroundResource(id);
        networkReloadBtn.setBackgroundResource(id);
        return this;
    }

    /**
     * 设置ReloadButton的监听器
     *
     * @param listener
     * @return
     */
    public LoadingLayout setOnReloadListener(OnReloadListener listener) {

        this.listener = listener;
        return this;
    }

    /**
     * 设置所有状态页的背景颜色
     *
     * @param color
     * @return
     */
    public LoadingLayout setStatusBackgroundColor(@ColorInt int color) {
        loadingPage.setBackgroundColor(color);
        errorPage.setBackgroundColor(color);
        emptyPage.setBackgroundColor(color);
        networkPage.setBackgroundColor(color);
        return this;

    }

    /**
     * 自定义加载页面，仅对当前所在的Activity有效
     *
     * @param view
     * @return
     */
    public LoadingLayout setLoadingPage(View view) {

        defineLoadingPage = view;
        this.removeView(loadingPage);
        defineLoadingPage.setVisibility(View.GONE);
        this.addView(view);
        return this;
    }

    /**
     * 自定义加载页面，仅对当前所在的地方有效
     *
     * @param id
     * @return
     */
    public LoadingLayout setLoadingPage(@LayoutRes int id) {

        this.removeView(loadingPage);
        View view = LayoutInflater.from(mContext).inflate(id, null);
        defineLoadingPage = view;
        defineLoadingPage.setVisibility(View.GONE);
        this.addView(view);
        return this;
    }


    /**
     * 设置加载失败图片宽高
     *
     * @param errorImgWidth
     * @param errorImgHeight
     * @return
     */
    public void setErrorImageWH(int errorImgWidth, int errorImgHeight) {
        ViewGroup.LayoutParams layoutParamsErrorImg = errorImg.getLayoutParams();
        layoutParamsErrorImg.width = errorImgWidth > 0 ? SizeUtils.dp2px(errorImgWidth) : errorImgWidth;
        layoutParamsErrorImg.height = errorImgHeight > 0 ? SizeUtils.dp2px(errorImgHeight) : errorImgHeight;
        errorImg.setLayoutParams(layoutParamsErrorImg);
    }

    /**
     * 、设置没有数据时图片宽高
     *
     * @param emptyImgWidth
     * @param emptyImgHeight
     * @return
     */
    public void setEmptyImageWH(int emptyImgWidth, int emptyImgHeight) {
        ViewGroup.LayoutParams layoutParamsEmptyImg = emptyImg.getLayoutParams();
        layoutParamsEmptyImg.width = emptyImgWidth > 0 ? SizeUtils.dp2px(emptyImgWidth) : emptyImgWidth;
        layoutParamsEmptyImg.height = emptyImgHeight > 0 ? SizeUtils.dp2px(emptyImgHeight) : emptyImgHeight;
        emptyImg.setLayoutParams(layoutParamsEmptyImg);
    }

    /**
     * 设置无网络时图片宽高
     *
     * @param networkImgWidth
     * @param networkImgHeight
     * @return
     */
    public void setNoNetworkImageWH(int networkImgWidth, int networkImgHeight) {
        ViewGroup.LayoutParams layoutParamsNetworkImg = networkImg.getLayoutParams();
        layoutParamsNetworkImg.width = networkImgWidth > 0 ? SizeUtils.dp2px(networkImgWidth) : networkImgWidth;
        layoutParamsNetworkImg.height = networkImgHeight > 0 ? SizeUtils.dp2px(networkImgHeight) : networkImgHeight;
        networkImg.setLayoutParams(layoutParamsNetworkImg);
    }

    /**
     * 设置提示文本与图片之间距离
     *
     * @param marginTop
     * @return
     */
    public void setTextMarginTop(int marginTop) {
        int i = marginTop > 0 ? SizeUtils.dp2px(marginTop) : marginTop;
        LinearLayout.LayoutParams layoutParams_errorText = (LinearLayout.LayoutParams) errorText.getLayoutParams();
        layoutParams_errorText.topMargin = i;
        errorText.setLayoutParams(layoutParams_errorText);

        LinearLayout.LayoutParams layoutParams_emptyText = (LinearLayout.LayoutParams) emptyText.getLayoutParams();
        layoutParams_emptyText.topMargin = i;
        emptyText.setLayoutParams(layoutParams_emptyText);

        LinearLayout.LayoutParams layoutParams_networkText = (LinearLayout.LayoutParams) networkText.getLayoutParams();
        layoutParams_networkText.topMargin = i;
        networkText.setLayoutParams(layoutParams_networkText);
    }


    /**
     * 设置重新加载按钮宽高
     *
     * @param width_dp
     * @param height_dp
     * @return
     */
    public void setReloadButtonWH(int width_dp, int height_dp) {
        if (buttonHeight != ViewGroup.LayoutParams.WRAP_CONTENT) {
            errorReloadBtn.setHeight(SizeUtils.dp2px(height_dp));
            networkReloadBtn.setHeight(SizeUtils.dp2px(height_dp));
        }
        if (buttonWidth != ViewGroup.LayoutParams.WRAP_CONTENT) {
            errorReloadBtn.setWidth(SizeUtils.dp2px(width_dp));
            networkReloadBtn.setWidth(SizeUtils.dp2px(width_dp));
        }
    }


    /**
     * 设置重新加载按钮内边距
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public void setReloadButtonPadding(int left, int top, int right, int bottom) {
        errorReloadBtn.setPadding(SizeUtils.dp2px(left), SizeUtils.dp2px(top), SizeUtils.dp2px(right), SizeUtils.dp2px(bottom));
        networkReloadBtn.setPadding(SizeUtils.dp2px(left), SizeUtils.dp2px(top), SizeUtils.dp2px(right), SizeUtils.dp2px(bottom));
    }


    /**
     * 获取当前自定义的loadingpage
     *
     * @return
     */
    public View getLoadingPage() {

        return defineLoadingPage;
    }


    /**
     * 获取全局使用的loadingpage
     *
     * @return
     */
    public View getGlobalLoadingPage() {

        return loadingPage;
    }

    @IntDef({Success, Empty, Error, No_Network, Loading})
    public @interface Flavour {

    }

    public interface OnReloadListener {

        void onReload(View v);
    }

    /**
     * 获取全局配置的class
     *
     * @return
     */
    public static Config getConfig() {

        return mConfig;
    }

    /**
     * 全局配置的Class，对所有使用到的地方有效
     */
    public static class Config {
        /**
         * 设置错误时文本提示
         *
         * @param text
         * @return
         */
        public Config setErrorText(@NonNull String text) {

            errorStr = text;
            return mConfig;
        }

        /**
         * 设置无数据时文本提示
         *
         * @param text
         * @return
         */
        public Config setEmptyText(@NonNull String text) {

            emptyStr = text;
            return mConfig;
        }

        /**
         * 设置无网络时文本提示
         *
         * @param text
         * @return
         */
        public Config setNoNetworkText(@NonNull String text) {

            netwrokStr = text;
            return mConfig;
        }

        /**
         * 设置重新加载按钮文本
         *
         * @param text
         * @return
         */
        public Config setReloadButtonText(@NonNull String text) {

            reloadBtnStr = text;
            return mConfig;
        }

        /**
         * 设置所有提示文本的字体大小
         *
         * @param sp
         * @return
         */
        public Config setAllTipTextSize(int sp) {

            tipTextSize = sp;
            return mConfig;
        }

        /**
         * 设置所有提示文本的字体颜色
         *
         * @param color
         * @return
         */
        public Config setAllTipTextColor(@ColorRes int color) {

            tipTextColor = color;
            return mConfig;
        }

        /**
         * 设置重新加载按钮文本大小
         *
         * @param sp
         * @return
         */
        public Config setReloadButtonTextSize(int sp) {

            buttonTextSize = sp;
            return mConfig;
        }

        public Config setReloadButtonVisibility(boolean reloadButtonVisibility) {
            isReloadButtonVisibility = reloadButtonVisibility;
            return mConfig;
        }

        /**
         * 设置重新加载按钮文本颜色
         *
         * @param color
         * @return
         */
        public Config setReloadButtonTextColor(@ColorRes int color) {

            buttonTextColor = color;
            return mConfig;
        }

        /**
         * 设置重新加载按钮背景颜色
         *
         * @param id
         * @return
         */
        public Config setReloadButtonBackgroundResource(@DrawableRes int id) {

            reloadBtnId = id;
            return mConfig;
        }

        /**
         * 设置重新加载按钮宽高
         *
         * @param width_dp
         * @param height_dp
         * @return
         */
        public Config setReloadButtonWH(int width_dp, int height_dp) {

            buttonWidth = width_dp;
            buttonHeight = height_dp;
            return mConfig;
        }

        /**
         * 设置加载失败图片
         *
         * @param id
         * @return
         */
        public Config setErrorImage(@DrawableRes int id) {

            errorImgId = id;
            return mConfig;
        }

        /**
         * 设置没有数据时图片
         *
         * @param id
         * @return
         */
        public Config setEmptyImage(@DrawableRes int id) {

            emptyImgId = id;
            return this;
        }

        /**
         * 设置无网络时图片
         *
         * @param id
         * @return
         */
        public Config setNoNetworkImage(@DrawableRes int id) {

            networkImgId = id;
            return mConfig;
        }

        /**
         * 设置加载失败图片宽高
         *
         * @param width_dp
         * @param height_dp
         * @return
         */
        public Config setErrorImageWH(int width_dp, int height_dp) {
            errorImgWidth = width_dp;
            errorImgHeight = height_dp;
            return mConfig;
        }

        /**
         * 、设置没有数据时图片宽高
         *
         * @param width_dp
         * @param height_dp
         * @return
         */
        public Config setEmptyImageWH(int width_dp, int height_dp) {
            emptyImgWidth = width_dp;
            emptyImgHeight = height_dp;
            return mConfig;
        }

        /**
         * 设置无网络时图片宽高
         *
         * @param width_dp
         * @param height_dp
         * @return
         */
        public Config setNoNetworkImageWH(int width_dp, int height_dp) {
            networkImgWidth = width_dp;
            networkImgHeight = height_dp;
            return mConfig;
        }

        /**
         * 设置提示文本与图片之间距离
         *
         * @param marginTop
         * @return
         */
        public Config setTextMarginTop(int marginTop) {
            marginTop_dp = marginTop;
            return mConfig;
        }

        public Config setLoadingPageLayout(@LayoutRes int id) {

            loadingLayoutId = id;
            return mConfig;
        }

        /**
         * 设置所有状态页的背景颜色
         *
         * @param color
         * @return
         */
        public Config setAllPageBackgroundColor(@ColorRes int color) {

            backgroundColor = color;
            return mConfig;
        }
    }
}
