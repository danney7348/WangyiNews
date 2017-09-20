package wangyi.bwie.com.wangyinews.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import wangyi.bwie.com.wangyinews.R;
import wangyi.bwie.com.wangyinews.bean.Popup;

/**
 * 作者： 张少丹
 * 时间：  2017/9/15.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class PopupAdapter extends BaseAdapter{
    private Context context;
   private List<Popup> popupList;

    public PopupAdapter(Context context, List<Popup> popupList) {
        this.context = context;
        this.popupList = popupList;
    }

    @Override
    public int getCount() {
        return popupList.size();
    }

    @Override
    public Object getItem(int i) {
        return popupList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        view = View.inflate(context, R.layout.popu_item,null);
        ImageView iv = view.findViewById(R.id.popup_iv);
        TextView tv = view.findViewById(R.id.popup_tv);
        iv.setImageResource(popupList.get(i).getImg());
        tv.setText(popupList.get(i).getName());
        return view;
    }
}
