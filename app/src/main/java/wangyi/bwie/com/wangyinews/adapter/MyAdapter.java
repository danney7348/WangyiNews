package wangyi.bwie.com.wangyinews.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import wangyi.bwie.com.wangyinews.R;
import wangyi.bwie.com.wangyinews.bean.News;

/**
 * 作者： 张少丹
 * 时间：  2017/9/14.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private List<News.ResultBean.DataBean> list;
    private final int atype = 0;
    private final int btype = 1;

    public MyAdapter(Context context, List<News.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void add( List<News.ResultBean.DataBean> list){
        list.addAll(list);
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return atype;
        }else {
            return btype;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        int type = getItemViewType(i);
        //初始化viewHolder
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2= null;
        if(view == null){
            switch (type){
                case atype:
                    //获取视图和控件
                    holder1 = new ViewHolder1();
                    view = View.inflate(context, R.layout.lv_item1, null);
                    holder1.iv1 = view.findViewById(R.id.iv1);
                    holder1.tv_name1 = view.findViewById(R.id.tv_name1);
                    holder1.tv_time1 = view.findViewById(R.id.tv_time1);
                    holder1.tv_title1 = view.findViewById(R.id.tv_title1);
                    view.setTag(holder1);
                    break;
                case btype:
                    //获取视图和控件
                    holder2 = new ViewHolder2();
                    view = View.inflate(context, R.layout.lv_item2, null);
                    holder2.iv2 = view.findViewById(R.id.iv2);
                    holder2.tv_name2 = view.findViewById(R.id.tv_name2);
                    holder2.tv_time2 = view.findViewById(R.id.tv_time2);
                    holder2.tv_title2 = view.findViewById(R.id.tv_title2);
                    view.setTag(holder2);
                    break;
            }
        }else{
            switch (type){
                case atype:
                    //绑定
                    holder1 = (ViewHolder1) view.getTag();
                    break;

                case btype:
                    //绑定
                    holder2 = (ViewHolder2) view.getTag();
                    break;
            }
        }
        switch (type){
            case atype:
                //赋值
                holder1.tv_title1.setText(list.get(i).getTitle());
                holder1.tv_name1.setText(list.get(i).getAuthor_name());
                holder1.tv_time1.setText(list.get(i).getDate());
                Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder1.iv1);
                break;

            case btype:
                //赋值
                holder2.tv_title2.setText(list.get(i).getTitle());
                holder2.tv_name2.setText(list.get(i).getAuthor_name());
                holder2.tv_time2.setText(list.get(i).getDate());
                Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder2.iv2);
                break;
        }
        return view;
    }
    //优化ViewHolder1
    public class ViewHolder1{
        public ImageView iv1;
        public TextView tv_title1,tv_time1,tv_name1;
    }
    //优化viewHolder2
    public class ViewHolder2{
        public ImageView iv2;
        public TextView tv_title2,tv_time2,tv_name2;
    }
}
