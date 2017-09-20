package wangyi.bwie.com.wangyinews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Moyuchen.networjudement.NetWorkjudeUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import view.xlistview.XListView;
import wangyi.bwie.com.wangyinews.R;
import wangyi.bwie.com.wangyinews.XiangqingActivity;
import wangyi.bwie.com.wangyinews.adapter.MyAdapter;
import wangyi.bwie.com.wangyinews.adapter.MyViewPagerAdapter;
import wangyi.bwie.com.wangyinews.bean.LixianNews;
import wangyi.bwie.com.wangyinews.bean.News;
import wangyi.bwie.com.wangyinews.bean.NewsListInterface;
import wangyi.bwie.com.wangyinews.bean.Url;
import wangyi.bwie.com.wangyinews.dao.NewsDao;
import wangyi.bwie.com.wangyinews.okhttpUtils.NewsDaoutils;

/**
 * 作者： 张少丹
 * 时间：  2017/9/13.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */
public class ShehuiFragment extends Fragment implements NewsListInterface, XListView.IXListViewListener {

    private View view;
    private XListView lv;
    private NewsDaoutils dao;
    private MyAdapter adapter;
    private List<News.ResultBean.DataBean> newlist = new ArrayList<>();
    private ViewPager vp;
    private TextView tv;
    private LinearLayout ll;
    private List<ImageView> imgList = new ArrayList<>();
    private List<String> vpList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), R.layout.news_item,null);
        dao = new NewsDaoutils();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = view.findViewById(R.id.lv);
        lv.setXListViewListener(this);
        lv.setPullRefreshEnable(true);
        lv.setPullLoadEnable(true);

        View inflate = View.inflate(getContext(), R.layout.viewpager, null);
        lv.addHeaderView(inflate);
        vp = inflate.findViewById(R.id.vp);
        tv = inflate.findViewById(R.id.vp_tv);
        ll = inflate.findViewById(R.id.ll_xiaoyuandian);
        NetWorkjudeUtils netWorkjudeUtils = new NetWorkjudeUtils();
        netWorkjudeUtils.judement(getContext(), new NetWorkjudeUtils.networkjude() {
            @Override
            public void Mobilenetwork() {
                Toast.makeText(getContext(), "Mobilenetwork", Toast.LENGTH_SHORT).show();
                dao.getList(Url.NEWS_SHEHUI);
            }

            @Override
            public void Wifinetwork() {
                Toast.makeText(getContext(), "Wifinetwork", Toast.LENGTH_SHORT).show();
                dao.getList(Url.NEWS_SHEHUI);
            }

            @Override
            public void UNnetwork() {

                Toast.makeText(getContext(), "UNnetwork", Toast.LENGTH_SHORT).show();
                NewsDao dao = new NewsDao(getContext());
                LixianNews query = dao.query("shehui");
                String content = query.getContent();
                parseData(content);

            }
        });

        dao.setNewsListInterface(this);
        vpList = new ArrayList<>();
        vpList.add("https://cbu01.alicdn.com/img/ibank/2016/031/397/3058793130_1828124284.310x310.jpg");
        vpList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3950662657,515060759&fm=27&gp=0.jpg");
        vpList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4098194244,60717967&fm=27&gp=0.jpg");
        vpList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1272234542,2063002059&fm=27&gp=0.jpg");
        MyViewPagerAdapter vpAdapter = new MyViewPagerAdapter(getActivity(), vpList);
        vp.setAdapter(vpAdapter);
        initDot();
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < imgList.size(); i++) {
                    if(position%vpList.size() == i){
                        imgList.get(i).setImageResource(R.drawable.dot1);
                    }else {
                        imgList.get(i).setImageResource(R.drawable.dot2);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initDot() {
        for (int i = 0; i < vpList.size(); i++) {
            ImageView iv = new ImageView(getContext());
            if(i == 0){
                iv.setImageResource(R.drawable.dot1);
            }else{
                iv.setImageResource(R.drawable.dot2);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10,10);
            params.setMargins(5,0,5,0);
            ll.addView(iv,params);
            imgList.add(iv);
        }
    }

    private void parseData(String content) {
        Gson gson = new Gson();
        System.out.println("content = " + content);
        News news = gson.fromJson(content, News.class);
        News.ResultBean result = news.getResult();
        final List<News.ResultBean.DataBean> data = result.getData();
        System.out.println("data = " + data);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(adapter == null){
                    adapter = new MyAdapter(getActivity(),newlist);
                    lv.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getContext(), XiangqingActivity.class);
                        intent.putExtra("url",data.get(i-2).getUrl());
                        intent.putExtra("title",data.get(i-2).getTitle());
                        startActivity(intent);
                    }
                });
            }
        });

    }

    @Override
    public void onNewsListFailure(Call call, IOException e) {

    }

    @Override
    public void onNewsListResponse(Call call, final List<News.ResultBean.DataBean> list) {

        newlist = list;
       getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(adapter == null){
                    adapter = new MyAdapter(getActivity(),newlist);
                    lv.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getContext(), XiangqingActivity.class);
                        intent.putExtra("url",list.get(i-2).getUrl());
                        intent.putExtra("title",list.get(i-2).getTitle());
                        startActivity(intent);
                    }
                });
            }
        });

    }

    @Override
    public void onRefresh() {

        if(adapter != null){
            adapter = null;
        }
        dao.getList(Url.NEWS_SHEHUI);
        lv.stopRefresh();

    }

    @Override
    public void onLoadMore() {
        adapter.add(newlist);
        lv.stopLoadMore();

    }
}
