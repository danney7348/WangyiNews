package wangyi.bwie.com.wangyinews.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import wangyi.bwie.com.wangyinews.R;
import wangyi.bwie.com.wangyinews.bean.Catogray;

/**
 * 作者： 张少丹
 * 时间：  2017/9/17.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class MyLixianAdapter extends BaseAdapter{
    private Context context;
    private List<Catogray> list;

    public MyLixianAdapter(Context context, List<Catogray> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.lixian_item,null);
        TextView tv = view.findViewById(R.id.lixian_tv);
        CheckBox cb = view.findViewById(R.id.lixian_cb);
        tv.setText(list.get(i).name);
        cb.setChecked(list.get(i).state);
        return view;
    }
}
