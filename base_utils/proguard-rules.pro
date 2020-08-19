# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepattributes EnclosingMethod
#指定代码的压缩级别
-optimizationpasses 5

#包明不混合大小写
-dontusemixedcaseclassnames

#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses

 #优化  不优化输入的类文件
-dontoptimize

 #预校验
-dontpreverify

 #混淆时是否记录日志
-verbose

 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护注解
-keepattributes *Annotation*


#忽略警告
#-ignorewarning

##记录生成的日志数据,gradle build时在本项目根目录输出##
#apk 包内所有 class 的内部结构
-dump proguard/class_files.txt
#未混淆的类和成员
-printseeds proguard/seeds.txt
#列出从 apk 中删除的代码
-printusage proguard/unused.txt
#混淆前后的映射
-printmapping proguard/mapping.txt
########记录生成的日志数据，gradle build时 在本项目根目录输出-end######
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
#如果引用了v4或者v7包
-dontwarn android.support.**

####混淆保护自己项目的部分代码以及引用的第三方jar包library-end####

#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable



#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#保持枚举 enum 类不被混淆
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}


#避免混淆泛型 如果混淆报错建议关掉
#-keepattributes Signature

#移除Log类打印各个等级日志的代码，打正式包的时候可以做为禁log使用，这里可以作为禁止log打印的功能使用，另外的一种实现方案是通过BuildConfig.DEBUG的变量来控制
#-assumenosideeffects class android.util.Log {
#    public static *** v(...);
#    public static *** i(...);
#    public static *** d(...);
#    public static *** w(...);
#    public static *** e(...);
#}

#############################################################################################
########################                 以上通用           ##################################
#############################################################################################

#######################     常用第三方模块的混淆选项         ###################################
#gson
#如果用用到Gson解析包的，直接添加下面这几行就能成功混淆，不然会报错。
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.boya.registerloginbinding.bean.** { *; }

#mob
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class m.framework.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn **.R$*

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#混淆淘宝
-dontwarn com.aliyun.api.**
-dontwarn com.dingtalk.api.**
-dontwarn com.qimen.api.**
-dontwarn com.qimencloud.api.**
-dontwarn com.taobao.api.**
-dontwarn okio.**
-dontwarn android.net.**
-keep class android.net.SSLCertificateSocketFactory{*;}
# ========= 友盟 =================
 -dontshrink
 -dontoptimize
 -dontwarn com.google.android.maps.**
 -dontwarn android.webkit.WebView
 -dontwarn com.umeng.**
 -dontwarn com.tencent.weibo.sdk.**
 -dontwarn com.facebook.**
 -keep public class javax.**
 -keep public class android.webkit.**
 -dontwarn android.support.v4.**
 -keep enum com.facebook.**
 -keepattributes Exceptions,InnerClasses,Signature
 -keepattributes *Annotation*
 -keepattributes SourceFile,LineNumberTable

 -keep public interface com.facebook.**
 -keep public interface com.tencent.**
 -keep public interface com.umeng.socialize.**
 -keep public interface com.umeng.socialize.sensor.**
 -keep public interface com.umeng.scrshot.**

 -keep public class com.umeng.socialize.* {*;}


 -keep class com.facebook.**
 -keep class com.facebook.** { *; }
 -keep class com.umeng.scrshot.**
 -keep public class com.tencent.** {*;}
 -keep class com.umeng.socialize.sensor.**
 -keep class com.umeng.socialize.handler.**
 -keep class com.umeng.socialize.handler.*
 -keep class com.umeng.weixin.handler.**
 -keep class com.umeng.weixin.handler.*
 -keep class com.umeng.qq.handler.**
 -keep class com.umeng.qq.handler.*
 -keep class UMMoreHandler{*;}
 -keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
 -keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
 -keep class im.yixin.sdk.api.YXMessage {*;}
 -keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
 -keep class com.tencent.mm.sdk.** {
    *;
 }
 -keep class com.tencent.mm.opensdk.** {
    *;
 }
 -keep class com.tencent.wxop.** {
    *;
 }
 -keep class com.tencent.mm.sdk.** {
    *;
 }
 -dontwarn twitter4j.**
 -keep class twitter4j.** { *; }

 -keep class com.tencent.** {*;}
 -dontwarn com.tencent.**
 -keep class com.kakao.** {*;}
 -dontwarn com.kakao.**
 -keep public class com.umeng.com.umeng.soexample.R$*{
     public static final int *;
 }
 -keep public class com.linkedin.android.mobilesdk.R$*{
     public static final int *;
 }
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 -keep class com.tencent.open.TDialog$*
 -keep class com.tencent.open.TDialog$* {*;}
 -keep class com.tencent.open.PKDialog
 -keep class com.tencent.open.PKDialog {*;}
 -keep class com.tencent.open.PKDialog$*
 -keep class com.tencent.open.PKDialog$* {*;}
 -keep class com.umeng.socialize.impl.ImageImpl {*;}
 -keep class com.sina.** {*;}
 -dontwarn com.sina.**
 -keep class  com.alipay.share.sdk.** {
    *;
 }

 -keepnames class * implements android.os.Parcelable {
     public static final ** CREATOR;
 }

 -keep class com.linkedin.** { *; }
 -keep class com.android.dingtalk.share.ddsharemodule.** { *; }
 -keepattributes Signature
######引用的其他Module可以直接在app的这个混淆文件里配置

# 如果使用了Gson之类的工具要使被它解析的JavaBean类即实体类不被混淆。
-keep class com.matrix.app.entity.json.** { *; }
-keep class com.matrix.appsdk.network.model.** { *; }

#####混淆保护自己项目的部分代码以及引用的第三方jar包library#######
#如果在当前的application module或者依赖的library module中使用了第三方的库，并不需要显式添加规则
#-libraryjars xxx
#添加了反而有可能在打包的时候遭遇同一个jar多次被指定的错误，一般只需要添加忽略警告和保持某些class不被混淆的声明。
#以libaray的形式引用了开源项目,如果不想混淆 keep 掉，在引入的module的build.gradle中设置minifyEnabled=false
-keep class com.nineoldandroids.** { *; }
-keep interface com.nineoldandroids.** { *; }
-dontwarn com.nineoldandroids.**
# 下拉刷新
-keep class in.srain.cube.** { *; }
-keep interface in.srain.cube.** { *; }
-dontwarn in.srain.cube.**
# observablescrollview：tab fragment
-keep class com.github.ksoichiro.** { *; }
-keep interface com.github.ksoichiro.** { *; }
#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}


#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}


#okio
-dontwarn okio.**
-keep class okio.**{*;}
-dontwarn com.github.ksoichiro.**

-keep class com.jph.takephoto.** { *; }
-dontwarn com.jph.takephoto.**

-keep class com.darsh.multipleimageselect.** { *; }
-dontwarn com.darsh.multipleimageselect.**

-keep class com.soundcloud.android.crop.** { *; }
-dontwarn com.soundcloud.android.crop.**
#rxjava
-dontwarn rx.**
-keep class rx.** { *; }

-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#glide混淆-开始
-keep public class * implements com.bumptech.glide.module.GlideModule
	-keep public class * extends com.bumptech.glide.module.AppGlideModule
	-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
	 **[] $VALUES;
	 public *;
	}


#glide混淆-结束

# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }
 #AndPermission
 -keepclassmembers class ** {
     @com.yanzhenjie.permission.PermissionYes <methods>;
 }
 -keepclassmembers class ** {
     @com.yanzhenjie.permission.PermissionNo <methods>;
 }
#友盟消息推送
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**

-keepattributes *Annotation*

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}

#（可选）避免Log打印输出
-assumenosideeffects class android.util.Log {
   public static *** v(...);
   public static *** d(...);
   public static *** i(...);
   public static *** w(...);
 }

 #友盟统计
 -keepclassmembers class * {
    public <init> (org.json.JSONObject);
 }
 -keep public class [com.boya.registerloginbinding].R$*{
 public static final int *;
 }
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }
 #微信混淆
 -keep class com.tencent.mm.opensdk.** {
    *;
 }
 -keep class com.tencent.wxop.** {
    *;
 }
 -keep class com.tencent.mm.sdk.** {
    *;
 }
 #AgentWeb
 -keep class com.just.agentweb.** {
     *;
 }
 -dontwarn com.just.agentweb.**

#掌赚宝混淆
 -keep class * implements android.os.Parcelable {    # 保持 Parcelable 不被混淆
   public static final android.os.Parcelable$Creator *;
 }

 -dontwarn okhttp3.**
 -keep class okhttp3.**{*;}
 -keep interface okhttp3.**{*;}

 -dontwarn okio.**
 -keep class okio.**{*;}
 -keep interface okio.**{*;}

 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public class * extends com.bumptech.glide.AppGlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }

 -dontwarn com.mx.lib.**
 -keep class com.mx.lib.**{*;}
 -keep class * implements android.os.Parcelable {    # 保持 Parcelable 不被混淆
   public static final android.os.Parcelable$Creator *;
 }

 -dontwarn okhttp3.**
 -keep class okhttp3.**{*;}
 -keep interface okhttp3.**{*;}

 -dontwarn okio.**
 -keep class okio.**{*;}
 -keep interface okio.**{*;}

 -dontwarn com.mx.lib.**
 -keep class com.mx.lib.**{*;}
 #有米混淆
 -keep public class com.mi.adtracker.MiAdTracker{ *; }
 #大头鸟混淆
 -dontwarn com.datouniao.**
#-libraryjars libs/com.datouniao.AdPublisher_v3.5.0.jar
-keep class com.datouniao.AdPublisher.** {*;}


#米积分
#mituo plat start
-keep class mituo.plat.** {*;}
-dontwarn mituo.plat.**

##picasso start
-dontwarn com.squareup.okhttp.**
-dontwarn okio.**
##picasso end

#okhttp start
-dontwarn okhttp3.**
-dontwarn okio.**
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
#okhttp end

##tencentloc start
-keepclassmembers class ** {
    public void on*Event(...);
}
-keep class c.t.**{*;}
-keep class com.tencent.map.geolocation.**{*;}
-keep class com.tencent.tencentmap.lbssdk.service.**{*;}

-dontwarn org.eclipse.jdt.annotation.**
-dontwarn c.t.**
##tencentloc end

#mituo plat end

#点财混淆规则
-keep class com.dc.wall.* { *;
}

#趣米
-keep class com.qumi.jfq.** {*;}
#万普平台
-keep public class cn.waps.** {*;}
-keep public interface cn.waps.** {*;}
-dontwarn cn.waps.**
#链家
# 忽略警告
-dontwarn android.support.**
-dontwarn com.fc.lk.sdk.**
# 避免库文件被混淆
-keep class com.fc.lk.sdk.** {*;}
-keep class com.fc.lk.sdk.ui.** {*;}
-keep class android.support.** {*;}
# 保护注解
-keepattributes *Annotation*
# 避免混淆泛型
-keepattributes Signature

# 避免 R 文件被混淆
-keepclassmembers class **.R$* {
public static <fields>;
}

#推啊
-keep class com.db.ta.sdk.** { *; }
#TONGDUN
-dontwarn android.os.**
-dontwarn com.android.internal.**
-keep class cn.tongdun.android.**{*;}

#竞价相关
-keep class com.kyview.** {*;}
-keep class com.kuaiyou.** {*;}
-dontwarn com.kyview.**
#快友
-keep class com.coolad.sdk.** {*;}
-keep class com.tencent.** {*;}
-ignorewarnings

#⼴广点通
-keep class com.qq.e.** {
public protected *;
}
-keep class android.support.v4.app.NotificationCompat**{
public *;
}
#百度
-keepclassmembers class * extends android.app.Activity {
public void *(android.view.View);
}
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}
-keep class com.baidu.mobads.*.** { *; }
#点开
-keep class com.alldk.** { *; }
# 360
-keep class com.ak.android.** {*;}
# 搜狗
-keep class com.sogou.feedads.**{*;}
#极光推送
-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }
#极光页面统计
-keep public class cn.jiguang.analytics.android.api.** {
        *;
    }
#极光分享
    -dontwarn cn.jiguang.**
    -keep class cn.jiguang.** { *; }
    -dontwarn cn.jpush.**
    -keep class cn.jpush.** { *; }
    -keep public class com.sina.** {
        *;
    }

    #x5内核
    -keep class com.tencent.smtt.** {*;}
    -keep class com.tencent.tbs.video.interfaces.** {*;}

#闲玩
 -dontoptimize
      -dontpreverify
      -dontwarn com.xianwan.sdklibrary.**
      -keep class com.xianwan.sdklibrary.*.** { *; }
      -keep class **.R$* {
       *;
      }
  #中亿
#-libraryjars libs/zy_sdk_1.2.1.jar
-dontwarn com.erm.integralwall.core.**
-dontwarn com.zy.phone.**
-keep class com.zy.phone.** { *; }
#聚享玩
-keep class com.toomee.mengplus.** {*;}
#开屏广告
-dontwarn com.adtest.**
-dontwarn com.qq.**
#通用配置
-keep class com.adtest.**{*;}
#如果接入的是整合广点通版本 需要添加如下配置
-keep class com.qq.e.** {
public protected *;
}
-keep class android.support.v4.app.NotificationCompat**{
public *;
}
#阅盟
-dontwarn com.iBookStar.**
-keep class com.iBookStar.**{ *;}
#合作混淆
-keep class com.wanappsdk.**{*;}

#穿山甲混淆
#保护内部类
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
-keep class com.bytedance.sdk.openadsdk.** {*;}
-keep class com.androidquery.callback.** {*;}
-keep public interface com.bytedance.sdk.openadsdk.downloadnew.** {*;}

#腾讯广告联盟
   -keep class com.qq.e.** {
        public protected *;
    }
    -keep class android.support.v4.**{
        public *;
    }
    -keep class android.support.v7.**{
        public *;
    }

 #百度广告 开始
-keepclassmembers class * extends android.app.Activity {
public void *(android.view.View);
}
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}
-keep class com.baidu.mobads.*.** { *; }
 #百度广告 结束

-keep class  com.zqy.zqy_util.diy_view.HorizontalProgressBarWithNumber{*;}
-keep class  com.zqy.zqy_util.diy_view.RoundProgressBarWidthNumber{*;}
-keep class com.zqy.zqy_util.util.AndroidInterface{*;}
-keep class com.zqy.zqy_util.ZqyManage {*;}