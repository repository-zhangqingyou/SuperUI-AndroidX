package com.zqy.nt.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
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
        mIcon =  findViewById(R.id.icon);
        mIcon.setOnClickListener(this);

      //  mIcon.setImageResource(androidx.appcompat.R.drawable.abc_ic_star_black_16dp);
        int abc_action_bar_home_description = androidx.appcompat.R.string.abc_action_bar_home_description;
        int actionBarDivider = androidx.appcompat.R.attr.actionBarDivider;
        int abc_action_bar_embed_tabs = androidx.appcompat.R.bool.abc_action_bar_embed_tabs;
        int[] actionBar = androidx.appcompat.R.styleable.ActionBar;
        int style = androidx.appcompat.R.style.Base_DialogWindowTitle_AppCompat;
        int abc_config_activityDefaultDur = androidx.appcompat.R.integer.abc_config_activityDefaultDur;
        int btn_checkbox_checked_mtrl_animation_interpolator_1 = androidx.appcompat.R.interpolator.btn_checkbox_checked_mtrl_animation_interpolator_1;
        int abc_fade_out = androidx.appcompat.R.anim.abc_fade_out;
        int abc_action_bar_content_inset_with_nav = androidx.appcompat.R.dimen.abc_action_bar_content_inset_with_nav;
        int abc_background_cache_hint_selector_material_light = androidx.appcompat.R.color.abc_background_cache_hint_selector_material_light;
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
}
