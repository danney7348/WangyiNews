package wangyi.bwie.com.wangyinews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import wangyi.bwie.com.wangyinews.R;

/**
 * 作者： 张少丹
 * 时间：  2017/9/14.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class MyViewPagerAdapter extends PagerAdapter{
    private Context context;
    private List<String> vpList;

    public MyViewPagerAdapter(Context context, List<String> vpList) {
        this.context = context;
        this.vpList = vpList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
         View view = View.inflate(context, R.layout.vp_item,null);
        ImageView vp = view.findViewById(R.id.vp_iv);
        Glide.with(context).load(vpList.get(position%vpList.size())).into(vp);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
