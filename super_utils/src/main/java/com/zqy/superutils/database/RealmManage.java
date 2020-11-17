//package com.zqy.superutils.database;
//
//import com.blankj.utilcode.util.AppUtils;
//import com.zqy.superutils.CacheUtil;
//
//import java.io.File;
//import java.util.List;
//
//import javax.annotation.Nullable;
//
//import io.realm.Realm;
//import io.realm.RealmAsyncTask;
//import io.realm.RealmChangeListener;
//import io.realm.RealmConfiguration;
//import io.realm.RealmObject;
//import io.realm.RealmQuery;
//import io.realm.RealmResults;
//
///**
// * 作者: zhangqingyou
// * 时间: 2020/7/3 9:09
// * 描述:
// * copyToRealm与copyToRealmOrUpdate：前者是给未指明主键的model使用的，后者则是给指明主键的model使用。
// * <p>
// * copyToRealm对应无主键参数的createObject，区别是copyToRealm会复用Realm缓存的model示例，不会每次都创建新的model实例。
// * <p>
// * copyToRealmOrUpdate对应有主键参数的createObject，区别是copyToRealmOrUpdate会复用Realm缓存的model示例，
// * 没有缓存实例就要去表中查询是否有匹配这个主键存在的记录，来决定是新建对象还是更新model实例
// */
//public class RealmManage {
//    private String DB_NAME;//数据库名字
//    private static RealmManage mInstance;
//
//    private RealmManage() {
//        //初始化数据库配置
//        DB_NAME = AppUtils.getAppName() + "_" + AppUtils.getAppPackageName();
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .name(DB_NAME)
//                .directory(new File(CacheUtil.getDatabasePath()))
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        Realm.setDefaultConfiguration(config);
//    }
//
//    public static RealmManage getInstance() {
//        if (mInstance == null) {
//            synchronized (RealmManage.class) {
//                if (mInstance == null) {
//                    mInstance = new RealmManage();
//                }
//            }
//        }
//        return mInstance;
//    }
//
//
//    /**
//     * 添加数据到本地数据库
//     *
//     * @param t   数据对象  和PrimaryKey（主键）匹配的数据会覆盖
//     * @param <T> 继承RealmObject的数据模型
//     */
//    public <T extends RealmObject> void addOrUpdate(T t) {
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(t);
//        realm.commitTransaction();
//    }
//
//    /**
//     * 添加数据到本地数据库
//     *
//     * @param tList 数据对象集合 和PrimaryKey（主键）匹配的数据会覆盖
//     * @param <T>   继承RealmObject的数据模型
//     */
//    public <T extends RealmObject> void addOrUpdate(List<T> tList) {
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(tList);
//        realm.commitTransaction();
//    }
//
//    /**
//     * 添加数据到本地数据库 (异步增加)
//     *
//     * @param tList 数据对象集合 和PrimaryKey（主键）匹配的数据会覆盖
//     * @param <T>   继承RealmObject的数据模型
//     */
//    public <T extends RealmObject> void addOrUpdateAsync(final List<T> tList) {
//        Realm realm = Realm.getDefaultInstance();
//        RealmAsyncTask addTask = realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.copyToRealmOrUpdate(tList);
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                // ToastUtil.tost("异步增加成功");
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                // ToastUtil.tost("异步增加失败");
//            }
//        });
//
//    }
//
//    /**
//     * 根据数据对象查询所有符合条件的数据 （同步查询）
//     *
//     * @param tClass 数据对象类
//     * @param <T>    继承RealmObject的数据模型
//     * @return
//     */
//    public <T extends RealmObject> List<T> searchAll(Class<T> tClass) {
//        Realm realm = Realm.getDefaultInstance();
//        RealmResults<T> result = realm.where(tClass)
//                .findAll();//同步查询
//        List<T> tList = realm.copyFromRealm(result);
//        return tList;
//    }
//
//    /**
//     * 根据数据对象查询所有符合条件的数据 (指定字段查询)
//     *
//     * @param tClass    数据对象类
//     * @param fieldName 数据对象的字段
//     * @param value     该数据字段的值
//     * @param <T>       继承RealmObject的数据模型
//     * @return
//     */
//    public <T extends RealmObject> List<T> searchAll(Class<T> tClass, String fieldName, @Nullable Object value) {
//        Realm mRealm = Realm.getDefaultInstance();
//        RealmResults<T> result = null;
//        if (value instanceof String) {
//            result = mRealm.where(tClass).equalTo(fieldName, (String) value).findAll();//同步查询
//        } else if (value instanceof Integer) {
//            result = mRealm.where(tClass).equalTo(fieldName, (Integer) value).findAll();//同步查询
//        }
//        List<T> tList = mRealm.copyFromRealm(result);
//        return tList;
//    }
//
//    /**
//     * 根据数据对象查询所有符合条件的数据 (指定字段查询)
//     *
//     * @param tClass        数据对象类
//     * @param fieldNameList 数据对象的字段
//     * @param valueList     该数据字段的值
//     * @param <T>           继承RealmObject的数据模型
//     * @return
//     */
//    public <T extends RealmObject> List<T> searchAll(Class<T> tClass, List<String> fieldNameList, List<String> valueList) {
//        Realm mRealm = Realm.getDefaultInstance();
//
//        RealmQuery<T> where = mRealm.where(tClass);
//        for (int i = 0; i < fieldNameList.size(); i++) {
//            String fieldName = fieldNameList.get(i);
//            String value = valueList.get(i);
//            where.equalTo(fieldName, value);
//        }
//        RealmResults<T> realmResults = where.findAll();//同步查询
//        List<T> tList = mRealm.copyFromRealm(realmResults);
//        return tList;
//    }
//
//    /**
//     * 查询所有符合条件的数据 （异步查询）
//     *
//     * @param tClass 数据对象类
//     * @param <T>    继承RealmObject的数据模型
//     * @return
//     */
//    public <T extends RealmObject> void searchAll(Class<T> tClass, final DataListener<T> dataListener) {
//        final Realm realm = Realm.getDefaultInstance();
//        final RealmResults<T> result = realm.where(tClass)
//                .findAllAsync();//异步查询
//
//        result.addChangeListener(new RealmChangeListener<RealmResults<T>>() {
//            @Override
//            public void onChange(RealmResults<T> ts) {
//                List<T> tList = realm.copyFromRealm(ts);
//                if (dataListener != null) {
//                    dataListener.onResult(tList);
//                }
//                result.removeChangeListener(this); //退出活动或片段时注销所有侦听器，以避免内存泄漏
//            }
//        });
//
//    }
//
//    /**
//     * 分页查询符合条件的数据· （同步查询）
//     *
//     * @param tClass 数据对象类
//     * @param page   页数
//     * @param size   页大小
//     * @param <T>    继承RealmObject的数据模型
//     * @return
//     */
//    public <T extends RealmObject> List<T> searchAll(Class<T> tClass, int page, int size) {
//        Realm realm = Realm.getDefaultInstance();
//        RealmResults<T> result = realm.where(tClass)
//                .findAll();
//
//        List<T> tList = result.subList((page - 1) * size, page * size);
//        return tList;
//    }
//
//    /**
//     * 查找满足查询条件的第一个对象
//     *
//     * @param tClass 数据对象类
//     * @param id     数据对象的id
//     * @param <T>    继承RealmObject的数据模型
//     * @return
//     */
//    public <T extends RealmObject> T search(Class<T> tClass, String id) {
//        Realm mRealm = Realm.getDefaultInstance();
//        T t = mRealm.where(tClass).equalTo("id", id).findFirst();
//        if (t != null) {
//            return mRealm.copyFromRealm(t);
//        }
//        return null;
//    }
//
//    /**
//     * 查找满足查询条件的第一个对象 (指定字段查询)
//     *
//     * @param tClass    数据对象类
//     * @param fieldName 数据对象的字段
//     * @param value     该数据字段的值
//     * @param <T>       继承RealmObject的数据模型
//     * @return
//     */
//    public <T extends RealmObject> T search(Class<T> tClass, String fieldName, @Nullable String value) {
//        Realm mRealm = Realm.getDefaultInstance();
//        T t = mRealm.where(tClass).equalTo(fieldName, value).findFirst();
//        if (t != null) {
//            return mRealm.copyFromRealm(t);
//        }
//        return null;
//    }
//
//
//    /**
//     * 根据id删除对象
//     *
//     * @param id     数据对象的id
//     * @param tClass 数据对象类
//     * @param <T>    继承RealmObject的数据模型
//     */
//    public <T extends RealmObject> void delete(Class<T> tClass, String id) {
//        Realm mRealm = Realm.getDefaultInstance();
//
//        RealmObject realmObject = mRealm.where(tClass).equalTo("id", id).findFirst();
//        if (realmObject != null) {
//            mRealm.beginTransaction();
//            realmObject.deleteFromRealm();
//            mRealm.commitTransaction();
//        }
//    }
//
//    /**
//     * 从领域删除指定类的所有对象
//     *
//     * @param tClass 数据对象类
//     * @param <T>    继承RealmObject的数据模型
//     */
//    public <T extends RealmObject> void deleteAll(Class<T> tClass) {
//        Realm mRealm = Realm.getDefaultInstance();
//
//        mRealm.beginTransaction();
//        mRealm.delete(tClass);
//        mRealm.commitTransaction();
//    }
//
//    /**
//     * 根据数据对象查询总数
//     *
//     * @param tClass 数据对象类
//     * @param <T>    继承RealmObject的数据模型
//     * @return
//     */
//    public <T extends RealmObject> int size(Class<T> tClass) {
//        Realm realm = Realm.getDefaultInstance();
//        RealmResults<T> result = realm.where(tClass)
//                .findAll();//同步查询
//        return result.size();
//    }
//}
