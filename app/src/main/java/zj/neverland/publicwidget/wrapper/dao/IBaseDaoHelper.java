package zj.neverland.publicwidget.wrapper.dao;

/**
 * Created by cefoc on 2017/5/2.
 * Class Note:
 */

public interface IBaseDaoHelper {
    //获取数据库
    <T> T getDbManager();
    //切换数据库
    void resetDaoConfig(String id);
    //创建数据库配置
    <T> T createDaoConfig();
    //获取数据库操作
    <T> T getProvider();
}
