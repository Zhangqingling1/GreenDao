package com.aqinga.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.aqinga.greendaodemo.greendao.gen.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edname;
    private Button intert;
    private Button delete;
    private Button uadate;
    private List<User> list;
    private MyAdapter adapter;
    private ListView listview;
    private EditText newname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initdata();
    }

    private void initdata() {
        //拿到一个集合
        list = GreenDaoManger.getInstance().getDaoSession().getUserDao().queryBuilder().build().list();
        adapter = new MyAdapter(this,list);
        listview.setAdapter(adapter);
    }
    //添加
    public void intert(Long id,String name){
        UserDao userDao = GreenDaoManger.getInstance().getDaoSession().getUserDao();
        User user = new User(id,name);
        userDao.insert(user);
        list.clear();
        list.addAll(userDao.queryBuilder().build().list());
        adapter.notifyDataSetChanged();
    }
    public void delete(String name){
        UserDao userDao = GreenDaoManger.getInstance().getDaoSession().getUserDao();
        List<User> list1 = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().list();
        if (list1!=null){
            for (User user:list1) {
                userDao.deleteByKey(user.getId());
                list.remove(user);
            }
            adapter.notifyDataSetChanged();
        }
    }
    public void update(String name,String newsname){
        UserDao userDao = GreenDaoManger.getInstance().getDaoSession().getUserDao();
        List<User> list2 = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().list();
       if (list2!=null){
           for (User user:list2) {
               user.setName(newsname);
               userDao.update(user);
           }
           adapter.notifyDataSetChanged();
           Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(this,"修改失败",Toast.LENGTH_SHORT).show();
       }
    }

    private void initview() {
        edname = (EditText) findViewById(R.id.et_Name);
        intert = (Button) findViewById(R.id.intert);
        delete = (Button) findViewById(R.id.delete);
        uadate = (Button) findViewById(R.id.update);
        newname = (EditText) findViewById(R.id.newsname);
        listview = (ListView) findViewById(R.id.list_view);
        intert.setOnClickListener(this);
        delete.setOnClickListener(this);
        uadate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.intert:
                intert(null,edname.getText().toString().trim());
                break;
            case R.id.delete:
                delete(edname.getText().toString().trim());
                break;
            case R.id.update:
                update(edname.getText().toString(),newname.getText().toString().trim());
                break;
        }
    }
}
