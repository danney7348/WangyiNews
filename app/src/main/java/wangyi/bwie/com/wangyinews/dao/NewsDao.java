package wangyi.bwie.com.wangyinews.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import wangyi.bwie.com.wangyinews.bean.LixianNews;
import wangyi.bwie.com.wangyinews.db.MySqliteOpenHelper;

/**
 * 作者： 张少丹
 * 时间：  2017/9/17.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class NewsDao {
    private Context context;
    private final MySqliteOpenHelper helper;

    public NewsDao(Context context) {
        this.context = context;
        helper = new MySqliteOpenHelper(context);
    }
    public void insert(String type,String content){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type",type);
        values.put("content",content);
        db.insert("news",null,values);
        db.close();
    }
    public LixianNews query(){
        LixianNews lixianNews = new LixianNews();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("news",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String content = cursor.getString(cursor.getColumnIndex("content"));
            lixianNews.setContent(content);
        }
        return  lixianNews;
    }
    public LixianNews query(String type){
        LixianNews lixianNews = new LixianNews();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from news where type = ?",new String[]{type});
        while(cursor.moveToNext()){
            String content = cursor.getString(cursor.getColumnIndex("content"));
            lixianNews.setContent(content);
        }
        return  lixianNews;
    }
}
