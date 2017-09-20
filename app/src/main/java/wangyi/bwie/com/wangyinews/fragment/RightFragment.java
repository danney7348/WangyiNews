package wangyi.bwie.com.wangyinews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import wangyi.bwie.com.wangyinews.DengluActivity;
import wangyi.bwie.com.wangyinews.R;

/**
 * 作者： 张少丹
 * 时间：  2017/9/14.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class RightFragment extends Fragment{

    private View view;
    private ImageView heitouxiang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.right_fragment,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        heitouxiang = view.findViewById(R.id.iv_heitouxiang);
        heitouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), DengluActivity.class);
                startActivity(intent);
            }
        });
    }
}
