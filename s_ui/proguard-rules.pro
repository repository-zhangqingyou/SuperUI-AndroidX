

# fastjson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }
-keepattributes Signature

# xpage
-keep class com.xuexiang.xpage.annotation.** { *; }
# xui
-keep class com.xuexiang.xui.widget.edittext.materialedittext.** { *; }
# agentweb
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**

#BannerViewPager
-keep class androidx.recyclerview.widget.**{*;}
-keep class androidx.viewpager2.widget.**{*;}
