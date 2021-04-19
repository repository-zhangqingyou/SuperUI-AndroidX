package com.zqy.superutils.manager;

import com.zqy.superutils.database.GreenDBHelper;

import org.greenrobot.greendao.AbstractDaoSession;

/**
 * 作者: zhangqingyou
 * 时间: 2021/1/28 15:21
 * 描述:GreenDB数据库管理 (适合项目中只有一个AbstractDaoSession的情况【不是和多module的模式】)
 */
public class GreenDBManager {
    private static GreenDBHelper greenDBHelper;

    /**
     * GreenDB数据库初始化 (不使用则不初始化)
     * 初始化示例：
     * // 常规SQLite数据库
     * GreenDaoContext greenDaoContext = new GreenDaoContext(this);//里面有个上下文GreenDaoContext继承了ContextWrapper,里面设置了数据库路径，
     * // greenDaoContext.setCurrentUserId(userId);
     * DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(greenDaoContext, AppUtils.getAppName() + "数据库");//
     * Database db = helper.getWritableDb();
     * // 数据库db = helper.getEncryptedWritableDb（“encryption-key”）;
     * DaoSession daoSession = new DaoMaster(db).newSession();
     * SuperUtilsManage.initGreenDB(daoSession);
     *
     * @param daoSession
     */
    protected static void init(AbstractDaoSession daoSession) {
        greenDBHelper = new GreenDBHelper(daoSession);
    }

    public static GreenDBHelper getGreenDBHelper() {
        return greenDBHelper;
    }
}
