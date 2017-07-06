package zj.neverland.publicwidget.wrapper.dao;

import android.content.Context;

import org.xutils.DbManager;
import org.xutils.x;

import zj.neverland.publicsdk.utils.PreferenceUtils;
import zj.neverland.publicsdk.utils.StringUtils;

/**
 * Created by cefoc on 2017/5/2.
 * Class Note:
 */

public class XUtilsDaoHelper implements IBaseDaoHelper {

    private Context context;
    private static DbManager.DaoConfig daoConfig = null; //数据库配置
    public static XUtilsDaoHelper instance = null;
    private static XUtilsDaoProvider daoProvider = null;

    public XUtilsDaoHelper(Context context) {
        this.context = context.getApplicationContext();
    }

    public static synchronized XUtilsDaoHelper getDbHelper(Context context) {
        if (instance == null) {
            instance = new XUtilsDaoHelper(context);
        }
        return instance;
    }

    @Override
    public DbManager getDbManager() {
        daoConfig = instance.createDaoConfig();
        return x.getDb(daoConfig);
    }

    @Override
    public void resetDaoConfig(String userid) {
        userid = userid.replace(".", "_");
        PreferenceUtils.setPrefString(context, "AUTO_USER_ID", userid);
        daoConfig = null;
        createDaoConfig();
    }

    @Override
    public DbManager.DaoConfig createDaoConfig() {
        if (daoConfig == null) {
            String auto_login_userid = PreferenceUtils.getPrefString(context, "AUTO_USER_ID", "");
            if (!StringUtils.isEmpty(auto_login_userid)) {
                daoConfig = new DbManager.DaoConfig()
                        .setDbName("cefoc_" + auto_login_userid)//创建数据库的名称
                        .setDbVersion(1)//数据库版本号
                        .setAllowTransaction(true)//设置是否开启事务,默认为false关闭事务
                        .setDbOpenListener(new DbManager.DbOpenListener() {
                            @Override
                            public void onDbOpened(DbManager db) {
                                db.getDatabase().enableWriteAheadLogging();
                            }
                        })
                        .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                            @Override
                            public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                                // TODO: ...
                                // db.addColumn(...);
                                // db.dropTable(...);
                                // ...
                            }
                        });//数据库更新操作
            }
        }
        return daoConfig;
    }

    @Override
    public XUtilsDaoProvider getProvider() {
        if(daoProvider!=null) {
            daoProvider = new XUtilsDaoProvider(instance);
        }
        return daoProvider;
    }
}
