package zj.neverland.publicwidget.wrapper.dao;

import android.content.Context;

/**
 * Created by cefoc on 2017/5/2.
 * Class Note:
 */

public class DaoManager {
    private Context context;
    private static IBaseDaoHelper daoHelper = null;

    public DaoManager(Context context) {
        this.context = context;
    }

    public IBaseDaoHelper getDbManager() {
        if (daoHelper == null)
            daoHelper = XUtilsDaoHelper.getDbHelper(context);
        return daoHelper;
    }

    public IBaseProvider getProvider() {
        if (daoHelper == null) {
            daoHelper = getDbManager();
        }
        IBaseProvider provider = daoHelper.getProvider();
        return provider;
    }
}
