package wangyi.bwie.com.wangyinews;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import wangyi.bwie.com.wangyinews.adapter.MyLixianAdapter;
import wangyi.bwie.com.wangyinews.bean.Catogray;
import wangyi.bwie.com.wangyinews.db.MySqliteOpenHelper;

import static wangyi.bwie.com.wangyinews.bean.Url.NEWS_LIST;

public class LixianActivity extends AppCompatActivity {

    private ListView lv;
    private Button btn;
    private List<Catogray> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lixian);
        initView();
        initData();
        initDown();

    }

    private void initDown() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list != null && list.size() > 0) {
                    for (Catogray catogray : list) {
                        if (catogray.state) {//判断是否是选中状态，如果选中则执行下载操作
                            loadData(catogray.type);
                            System.out.println("catogray = " + catogray.state);
                        }
                    }
                }
                for (Catogray catogray : list) {
                    System.out.println("state====" + catogray.state);
                }
            }
        });
    }

    private void loadData(final String type) {

        RequestParams params = new RequestParams(NEWS_LIST);
        params.addParameter("type", type);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                saveData(type, result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void saveData(String type, String result) {
        MySqliteOpenHelper helper = new MySqliteOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type",type);
        values.put("content",result);
        db.insert("news",null,values);
        Toast.makeText(this, "下载完毕", Toast.LENGTH_SHORT).show();
    }

    private void initData() {

        list = new ArrayList<>();
        Catogray c = new Catogray();
        c.type = "top";
        c.name = "头条";
        list.add(c);
        c = new Catogray();
        c.type = "yule";
        c.name = "娱乐";
        list.add(c);
        /**
         * "头条","娱乐",
         "财经","科技","社会","军事",
         "时尚","时尚","国内","国外"
         */
        c = new Catogray();
        c.type = "shehui";
        c.name = "社会";
        list.add(c);
        c = new Catogray();
        c.type = "tiyu";
        c.name = "体育";
        list.add(c);
        c = new Catogray();
        c.type = "keji";
        c.name = "科技";
        list.add(c);
        c = new Catogray();
        c.type = "guoji";
        c.name = "国际";
        list.add(c);
        c = new Catogray();
        c.type = "guonei";
        c.name = "国内";
        list.add(c);
        c = new Catogray();
        c.type = "caijing";
        c.name = "财经";
        list.add(c);
        c = new Catogray();
        c.type = "shishang";
        c.name = "时尚";
        list.add(c);
        c = new Catogray();
        c.type = "junshi";
        c.name = "军事";
        list.add(c);
        MyLixianAdapter adapter = new MyLixianAdapter(this,list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckBox checkbox = view.findViewById(R.id.lixian_cb);
                Catogray c = list.get(i);
                if (checkbox.isChecked()) {
                    checkbox.setChecked(false);
                    c.state = false;
                } else {
                    checkbox.setChecked(true);
                    c.state = true;
                }
                list.set(i, c);
            }
        });

    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lixian_lv);
        btn = (Button) findViewById(R.id.lixian_btn);

    }
}
