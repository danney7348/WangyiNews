package wangyi.bwie.com.wangyinews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class XiangqingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        WebView wv = (WebView) findViewById(R.id.wv);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);
        setTitle(title);
    }
    public void dianji(View view){
        Intent intent1=new Intent(Intent.ACTION_SEND);
        intent1.putExtra(Intent.EXTRA_TEXT,"This is my text to send");
        intent1.setType("text/plain");
        startActivity(Intent.createChooser(intent1,"share"));
    }
}
