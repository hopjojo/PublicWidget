package zj.neverland.publicwidget.wrapper.dao;

import android.database.Cursor;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.io.IOException;

/**
 * Created by cefoc on 2017/5/2.
 * Class Note:
 */

public class XUtilsDaoProvider implements IBaseProvider {
    private XUtilsDaoHelper helper;
    private DbManager db;
    public XUtilsDaoProvider(XUtilsDaoHelper helper){
        this.helper = helper;
        this.db = helper.getDbManager();
    }

    @Override
    public void save(Object o) {
        try {
            db.saveOrUpdate(o);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void replace(Object o) {
        try {
            db.replace(o);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object o) {
        try {
            db.delete(o);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
//        db.update();
    }

    @Override
    public void find() {

    }

    @Override
    public void dropTable() {

    }

    @Override
    public void addColumn() {

    }

    @Override
    public void dropDB() {

    }

    @Override
    public void close() {
        try {
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execNonQuery(String sql) {
        try {
            db.execNonQuery(sql);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cursor execQuery(String sql) {
        try {
            return db.execQuery(sql);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }
}
