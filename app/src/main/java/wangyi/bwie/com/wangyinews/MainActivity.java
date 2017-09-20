package wangyi.bwie.com.wangyinews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.Moyuchen.networjudement.NetWorkjudeUtils;
import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.example.kson.tablayout.widget.HorizontalScollTabhost;
import com.example.kson.tablayout.widget.bean.CategoryBean;
import com.kson.slidingmenu.SlidingMenu;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import wangyi.bwie.com.wangyinews.adapter.PopupAdapter;
import wangyi.bwie.com.wangyinews.bean.Popup;
import wangyi.bwie.com.wangyinews.fragment.CaijingFragment;
import wangyi.bwie.com.wangyinews.fragment.GuojiFragment;
import wangyi.bwie.com.wangyinews.fragment.GuoneiFragment;
import wangyi.bwie.com.wangyinews.fragment.JunshiFragment;
import wangyi.bwie.com.wangyinews.fragment.KejiFragment;
import wangyi.bwie.com.wangyinews.fragment.LeftFragment;
import wangyi.bwie.com.wangyinews.fragment.MyFragment;
import wangyi.bwie.com.wangyinews.fragment.RightFragment;
import wangyi.bwie.com.wangyinews.fragment.ShehuiFragment;
import wangyi.bwie.com.wangyinews.fragment.ShishangFragment;
import wangyi.bwie.com.wangyinews.fragment.TopFragment;
import wangyi.bwie.com.wangyinews.fragment.YuleFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HorizontalScollTabhost hts;
    private List<CategoryBean> list;
    private List<Fragment> fragments;
    private ImageView santiao;
    private ImageView biaotitouxiang;
    private SlidingMenu menu;
    private ImageView xiangxia;
    private String[] titles = {"头条","娱乐",
            "财经","科技","社会","军事",
            "时尚","时尚","国内","国外"};
    private SharedPreferences sp;
    private String json;
    private ImageView sandian;
    private View popupView;
    private ListView popup_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        initView();
        initData();
        initMenu();
        new NetWorkjudeUtils().judement(this, new NetWorkjudeUtils.networkjude() {
            @Override
            public void Mobilenetwork() {

            }

            @Override
            public void Wifinetwork() {

            }

            @Override
            public void UNnetwork() {

            }
        });
    }
    private void initMenu() {

        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        menu.setMenu(R.layout.left_fragment_content);
        getSupportFragmentManager().beginTransaction().replace(R.id.left_fragment_content,new LeftFragment()).commit();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.KuanduLetf);
        menu.setSecondaryMenu(R.layout.right_fragment_content);
        getSupportFragmentManager().beginTransaction().replace(R.id.right_fragment_content,new RightFragment()).commit();
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
    }

    private void initView() {//iv_sandian
        hts = (HorizontalScollTabhost) findViewById(R.id.hst);
        santiao = (ImageView) findViewById(R.id.iv_santiao);
        sandian = (ImageView) findViewById(R.id.iv_sandian);
        biaotitouxiang = (ImageView) findViewById(R.id.iv_biaotitouxiang);
        santiao.setOnClickListener(this);
        biaotitouxiang.setOnClickListener(this);
        sandian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Popup> popupList=new ArrayList<>();
                Popup popup1=new Popup("天气",R.drawable.tianqitupian);
                Popup popup2=new Popup("离线",R.drawable.lixiantupian);
                Popup popup3=new Popup("夜间",R.drawable.yejiantupian);
                Popup popup4=new Popup("搜索",R.drawable.sousuotupian);
                Popup popup5=new Popup("扫一扫",R.drawable.saoyisaotupian);
                Popup popup6=new Popup("设置",R.drawable.shezhitupian);
                popupList.add(popup1);
                popupList.add(popup2);
                popupList.add(popup3);
                popupList.add(popup4);
                popupList.add(popup5);
                popupList.add(popup6);
                popupView = MainActivity.this.getLayoutInflater().inflate(R.layout.popupwindow, null);
                popup_lv = popupView.findViewById(R.id.popup_lv);
                PopupAdapter popupAdapter=new PopupAdapter(MainActivity.this,popupList);
                popup_lv.setAdapter(popupAdapter);
                popup_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if(i==0){
                            Toast.makeText(MainActivity.this, "0", Toast.LENGTH_SHORT).show();
                        }
                        if(i==1){
                            Intent intent = new Intent(MainActivity.this,LixianActivity.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                        }
                        if(i==2){
                            int uiMode;
                            uiMode = getResources().getConfiguration().uiMode& Configuration.UI_MODE_NIGHT_MASK;
                            if(uiMode == Configuration.UI_MODE_NIGHT_YES){
                                getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                                getSharedPreferences("theme",MODE_PRIVATE).edit().putBoolean("night_theme",false).commit();
                            }else if(uiMode == Configuration.UI_MODE_NIGHT_NO){
                                getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                               getSharedPreferences("theme",MODE_PRIVATE).edit().putBoolean("night_theme", true).commit();
                            }
                            recreate();
                            Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                        }
                        if(i==3){
                            Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
                        }
                        if(i==4){
                            Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();
                        }
                        if(i==5){
                            Toast.makeText(MainActivity.this, "5", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,ShezhiActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                PopupWindow popupWindow=new PopupWindow(popupView,400,600);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#EEEEEE")));
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.update();
                popupWindow.showAsDropDown(sandian,0,0);
            }
        });

        xiangxia = hts.findViewById(R.id.iv_xiangxiadejiantou);
        xiangxia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<ChannelBean> listChannel = new ArrayList<ChannelBean>();
                String json = sp.getString("json", null);
                if(json == null){
                    ChannelBean channelBean;
                    for (int i = 0; i <titles.length ; i++) {
                        if(i<8){
                            channelBean = new ChannelBean(titles[i],true);
                        }else{
                            channelBean = new ChannelBean(titles[i],false);
                        }
                        listChannel.add(channelBean);
                    }
                }else{
                    //不为空使用之前回传的数据
                    try {
                        JSONArray arr = new JSONArray(json);
                        System.out.println("arr.toString() = " + arr.toString());
                        for (int i = 0; i <arr.length() ; i++) {
                            JSONObject o = (JSONObject) arr.get(i);
                            String name = o.getString("name");
                            boolean isSelect = o.getBoolean("isSelect");
                            ChannelBean channelBean = new ChannelBean(name,isSelect);
                            listChannel.add(channelBean);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ChannelActivity.startChannelActivity(MainActivity.this,listChannel);
            }
        });
    }
    private void initData() {
        list = new ArrayList<>();
        fragments = new ArrayList<>();
        CategoryBean bean;
        for (int i = 0; i <titles.length ; i++) {
            bean = new CategoryBean();
            bean.name = titles[i];
            list.add(bean);
            fragments.add(new TopFragment());
            fragments.add(new YuleFragment());
            fragments.add(new CaijingFragment());
            fragments.add(new KejiFragment());
            fragments.add(new ShehuiFragment());
            fragments.add(new JunshiFragment());
            fragments.add(new ShishangFragment());
            fragments.add(new ShishangFragment());
            fragments.add(new GuoneiFragment());
            fragments.add(new GuojiFragment());
        }
        hts.diaplay(list, fragments);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_santiao:
                menu.showMenu();
                break;
            case R.id.iv_biaotitouxiang:
                menu.showSecondaryMenu();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 101){
            json = data.getStringExtra("json");
            sp.edit().putString("json", json).commit();
            list.clear();
            List<Fragment> fragmentList2 = new ArrayList<>();
            try {
                JSONArray arr = new JSONArray(json);
                for (int i = 0; i <arr.length() ; i++) {
                    JSONObject oo = (JSONObject) arr.get(i);
                    String name = oo.getString("name");
                    boolean isSelect = oo.getBoolean("isSelect");
                    if(isSelect){
                        CategoryBean c = new CategoryBean();
                        c.name = name;
                        list.add(c);
                        fragmentList2.add(fragments.get(i));
                    }
                }
                hts.remove();
                hts.diaplay(list,fragmentList2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
