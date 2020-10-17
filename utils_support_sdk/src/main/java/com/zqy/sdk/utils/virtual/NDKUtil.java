package com.zqy.sdk.utils.virtual;

/**
 * Project Name:EasyProtector
 * Package Name:com.zqy.sdk.virtual
 * Created by lahm on 2018/5/14 下午9:59 .
 */
public class NDKUtil {
    private static volatile boolean mIsLibLoaded = false;
    private static volatile boolean mIsNativeInited = false;

    private static com.zqy.sdk.utils.virtual.LibLoader localLibLoader = new com.zqy.sdk.utils.virtual.LibLoader() {
        @Override
        public void loadLibrary(String libName) throws UnsatisfiedLinkError, SecurityException {
            System.loadLibrary(libName);
        }
    };

    /**
     * 这个方法只是用来加载antitrace.so的
     *
     * @param libLoader
     */
    public static void loadLibrariesOnce(com.zqy.sdk.utils.virtual.LibLoader libLoader) {
        synchronized (NDKUtil.class) {
            if (!mIsLibLoaded) {
                if (libLoader == null) {
                    libLoader = localLibLoader;
                }
                libLoader.loadLibrary("antitrace");
                mIsLibLoaded = true;
            }
        }
    }

    /**
     * 如果想用NDKUtil加载其他so库，用这个方法
     *
     * @param libName
     */
    public static void loadLibraryByName(String libName) {
        if (libName == null || "".equals(libName)) return;
        synchronized (NDKUtil.class) {
            localLibLoader.loadLibrary(libName);
        }
    }

    public NDKUtil() {
        this(localLibLoader);
    }

    public NDKUtil(com.zqy.sdk.utils.virtual.LibLoader libLoader) {
        initNDK(libLoader);
    }

    private void initNDK(LibLoader libLoader) {
        loadLibrariesOnce(libLoader);
        initNativeOnce();
    }

    private void initNativeOnce() {
        synchronized (NDKUtil.class) {
            if (!mIsNativeInited) {
//                native_init();
                mIsNativeInited = true;
            }
        }
    }

//    private static native void native_init();
}