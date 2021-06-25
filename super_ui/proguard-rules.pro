

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
#qmui
-keep class **_FragmentFinder { *; }
-keep class androidx.fragment.app.* { *; }
-keep class com.qmuiteam.qmui.arch.record.RecordIdClassMap { *; }
-keep class com.qmuiteam.qmui.arch.record.RecordIdClassMapImpl { *; }
-keep class com.qmuiteam.qmui.arch.scheme.SchemeMap {*;}
-keep class com.qmuiteam.qmui.arch.scheme.SchemeMapImpl {*;}


#PictureSelector 2.0
-keep class com.luck.picture.lib.** { *; }

#Ucrop
-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }

#Okio
-dontwarn org.codehaus.mojo.animal_sniffer.*
