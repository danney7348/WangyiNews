package wangyi.bwie.com.wangyinews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import wangyi.bwie.com.wangyinews.MainActivity;
import wangyi.bwie.com.wangyinews.R;

/**
 * 作者： 张少丹
 * 时间：  2017/9/14.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class LeftFragment extends Fragment{

    private View view;
    private LinearLayout ll_xinwen,ll_dingyue,ll_tupian,ll_shipin,ll_gentie;
    private TextView tv_xinwen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.left_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        ll_xinwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        ll_dingyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        ll_shipin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        ll_tupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        ll_gentie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void initView() {
        ll_xinwen = view.findViewById(R.id.ll_xinwen);
        ll_dingyue = view.findViewById(R.id.ll_dingyue);
        ll_tupian = view.findViewById(R.id.ll_tupian);
        ll_shipin = view.findViewById(R.id.ll_shipin);
        ll_gentie = view.findViewById(R.id.ll_gentie);
        tv_xinwen = view.findViewById(R.id.tv_xinwen);
    }
}
