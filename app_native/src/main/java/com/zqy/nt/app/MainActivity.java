package com.zqy.nt.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatImageView mIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // ;
    }

    private void initView() {
        mIcon = findViewById(R.id.icon);
        mIcon.setOnClickListener(this);

        getLayoutId();

        getStringId();

        getDrawableId();

        getStyleId();

        getStyleableId();

        getId();
        getColorId();

        getAnimId();

        getDimen();


        getAttr();

        getBool();

        getInteger();

        getInterpolator();

        getXml();

        getPlurals();

        getAnimator();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.icon:
                break;
        }
    }

    public int getLayoutId() {
        int layout = androidx.appcompat.R.layout.abc_action_bar_up_container;
        Logger.d("layout：" + layout);
        return layout;
    }

    public int getStringId() {
        int string = androidx.appcompat.R.string.abc_action_bar_up_description;
        Logger.d("string：" + string);
        return string;
    }

    public int getDrawableId() {
        int drawable = androidx.appcompat.R.drawable.abc_action_bar_item_background_material;
        Logger.d("drawable：" + drawable);
        return drawable;
    }
    //androidx里面没有mipmap图片
//    public int getMipmap( ) {
//        int mipmap = androidx.appcompat.R.mipmap.abc_action_bar_item_background_material;
//        return mipmap;
//    }

    public int getStyleId() {
        int style = androidx.appcompat.R.style.AlertDialog_AppCompat_Light;
        Logger.d("style：" + style);
        return style;
    }

    public int getStyleableId() {
        int styleable = androidx.appcompat.R.styleable.ActionBar_contentInsetStartWithNavigation;
        Logger.d("styleable：" + styleable);
        return styleable;
    }

    public int getId() {
        int id = androidx.appcompat.R.id.accessibility_custom_action_10;
        Logger.d("id：" + id);
        return id;
    }

    public int getColorId() {
        int color = androidx.appcompat.R.color.abc_background_cache_hint_selector_material_light;
        Logger.d("color：" + color);
        return color;
    }
    //androidx里面没有raw
//    public int getRawId() {
//        int raw = androidx.appcompat.R.raw.abc_background_cache_hint_selector_material_light;
//        return raw;
//    }

    public int getAnimId() {
        int anim = androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom;
        Logger.d("anim：" + anim);
        return anim;
    }

    public int getDimen() {
        int dimen = androidx.appcompat.R.dimen.abc_action_bar_content_inset_with_nav;
        Logger.d("dimen：" + dimen);
        return dimen;
    }


    public int getAttr() {
        int attr = androidx.appcompat.R.attr.actionBarItemBackground;
        Logger.d("attr：" + attr);
        return attr;
    }

    public int getBool() {
        int bool = androidx.appcompat.R.bool.abc_allow_stacked_button_bar;
        Logger.d("bool：" + bool);
        return bool;
    }

    public int getInteger() {
        int integer = androidx.appcompat.R.integer.abc_config_activityShortDur;
        Logger.d("integer：" + integer);
        return integer;
    }

    public int getInterpolator() {
        int interpolator = androidx.appcompat.R.interpolator.btn_checkbox_unchecked_mtrl_animation_interpolator_1;
        Logger.d("interpolator：" + interpolator);
        return interpolator;
    }

    public int getXml() {
        int xml = com.google.android.material.R.xml.standalone_badge_gravity_bottom_end;
        Logger.d("xml：" + xml);
        return xml;
    }

    public int getPlurals() {
        int plurals = com.google.android.material.R.plurals.mtrl_badge_content_description;
        Logger.d("plurals：" + plurals);
        return plurals;
    }

    public int getAnimator() {
        int animator = com.google.android.material.R.animator.mtrl_btn_unelevated_state_list_anim;
        Logger.d("animator：" + animator);
        return animator;
    }
}
