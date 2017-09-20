package wangyi.bwie.com.wangyinews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class QidongActivity extends AppCompatActivity {

    private ViewPager vp;

    private int[] imgs = {R.drawable.a,R.drawable.b,R.drawable.c};

    private Button qd_btn;
    private SharedPreferences isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qidong);
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        isFirst = getSharedPreferences("qidong", MODE_PRIVATE);

        vp = (ViewPager) findViewById(R.id.vp);
        qd_btn = (Button) findViewById(R.id.qd_btn);

        // 第二次进入页面，进入到启动页Main2Activity
        boolean b = isFirst.getBoolean("qidong", false);
        if(b){
            Intent intent = new Intent(QidongActivity.this,Main2Activity.class);
            startActivity(intent);
            finish();
        }


        //点击按钮跳转
        qd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 第1次进入页面，进入到主页MainActivity
                boolean b= isFirst.edit().putBoolean("qidong", true).commit();
                if(b){
                    Intent intent = new Intent(QidongActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }



            }
        });

        vp.setAdapter(new MyPagerAdapter());

        //ViewPager的页面监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //按钮的显示
                if(position == imgs.length -1){
                    qd_btn.setVisibility(View.VISIBLE);
                }else{
                    qd_btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(QidongActivity.this, R.layout.qidong_vp_item, null);
            ImageView vp_imgs = view.findViewById(R.id.vp_imgs);
            vp_imgs.setImageResource(imgs[position]);

            //加载到viewpager上面
            container.addView(view);
            return view;
        }
    }
}
