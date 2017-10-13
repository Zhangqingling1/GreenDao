package com.aqinga.greendao;

import com.aqinga.greendaodemo.greendao.gen.DaoMaster;
import com.aqinga.greendaodemo.greendao.gen.DaoSession;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/10/1311:21
 */


public class GreenDaoManger {
    private static GreenDaoManger mInstance;
    private static GreenDaoManger manger;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public GreenDaoManger() {
        //创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApp.getContext(), "zhangqingling.db", null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static GreenDaoManger getInstance() {
        if (mInstance==null){
            mInstance = new GreenDaoManger();
        }
        return mInstance;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
    public DaoSession getNewSession(){
         daoSession = daoMaster.newSession();
        return daoSession;
    }
}
