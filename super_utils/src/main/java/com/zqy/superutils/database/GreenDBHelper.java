package com.zqy.superutils.database;

import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2021/1/28 15:21
 * 描述:GreenDB数据库管理 (适合项目中只有一个AbstractDaoSession的情况【不是和多module的模式】)
 */
public class GreenDBHelper {
    private AbstractDaoSession abstractDaoSession;

    public GreenDBHelper(AbstractDaoSession abstractDaoSession) {
         /*       // 常规SQLite数据库
        GreenDaoContext greenDaoContext = new GreenDaoContext();//里面有个上下文GreenDaoContext继承了ContextWrapper,里面设置了数据库路径，
        greenDaoContext.setCurrentUserId(userId);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(greenDaoContext, AppUtils.getAppName() + "数据库");//
        Database db = helper.getWritableDb();

        // 数据库db = helper.getEncryptedWritableDb（“encryption-key”）;
        daoSession = new DaoMaster(db).newSession();*/
        this.abstractDaoSession = abstractDaoSession;
    }


    public AbstractDaoSession getDaoSession() {
        return abstractDaoSession;
    }


    /**
     * 根据实体类,插件或修改信息
     *
     * @param entity 实体类
     * @return 插件或修改的数据id
     */
    public <T> long insert(T entity) {
        return getDaoSession().insert(entity);
    }

    /**
     * 根据实体类,插件或修改信息
     *
     * @param entity 实体类
     * @return 插件或修改的数据id
     */
    public <T> long insertOrReplace(T entity) {
        return getDaoSession().insertOrReplace(entity);
    }


    /**
     * 查询所有,返回数据列表
     *
     * @param entityClass
     * @return 数据列表
     */
    public <T> List<T> loadAll(Class<T> entityClass) {
        return getDaoSession().loadAll(entityClass);
    }

    /**
     * 条件查询
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> querydataBy(Class<T> tClass, WhereCondition cond, WhereCondition... condMore) {
        Query<T> nQuery = getDaoSession().queryBuilder(tClass)
                .where(cond, condMore)//查询条件
                //.where(UserDao.Properties.Id.notEq(999))
                //.orderAsc(UserDao.Properties.Age)//订购Asc
                //  .limit(5)//返回页数
                .build();
        return querydataBy(nQuery);

    }

    /**
     * 条件查询
     *
     * @param nQuery 实体类
     * @param <T>
     * @return
     */
    public <T> List<T> querydataBy(Query<T> nQuery) {
        List<T> tList = nQuery.list();
        return tList;

    }

    /**
     * 删除所有数据
     */
    public <T> void deleteAll(Class<T> entityClass) {
        getDaoSession().deleteAll(entityClass);
    }

    /**
     * 根据实体类,删除信息
     *
     * @param entity 实体类
     */
    public <T> void delete(T entity) {
        getDaoSession().delete(entity);
    }
}
