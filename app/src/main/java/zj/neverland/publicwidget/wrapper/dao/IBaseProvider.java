package zj.neverland.publicwidget.wrapper.dao;

import android.database.Cursor;

/**
 * Created by cefoc on 2017/5/2.
 * Class Note:
 */

public interface IBaseProvider<T> {
    //插入数据
    void save(T t);
    //插入数据,存在唯一索引时才有用
    void replace(T t);
    //删除数据
    void delete(T t);
    //更新数据
    void update();
    //查询数据
    void find();
    //删除表
    void dropTable();
    //添加一列
    void addColumn();
    //删除数据库
    void dropDB();

    void close();
    ///////////// custom
//    void execNonQuery(SqlInfo sqlInfo);
    void execNonQuery(String sql);
//    Cursor execQuery(SqlInfo sqlInfo);
    Cursor execQuery(String sql);
}
