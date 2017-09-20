package wangyi.bwie.com.wangyinews;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.liu.asus.clearcun.CliearUyils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;
import cn.jpush.android.data.JPushView;
import wangyi.bwie.com.wangyinews.bean.Config;
import wangyi.bwie.com.wangyinews.bean.Version;

public class ShezhiActivity extends AppCompatActivity {

    private TextView tv_clear;
    private LinearLayout ll_clear;
    private TextView tv_size;
    private Switch sw_tuisong;
    private LinearLayout ll_jiancha;
    private String url = "http://125.39.134.47/r/a.gdown.baidu.com/data/wisegame/7c28ac069399b336/kuaishou_4812.apk";
    private ProgressDialog progressDialog;//加载框
    private Callback.Cancelable cancelable;//请求任务对象
    private TextView tv_versioncode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        initView();
        initData();
        initDialog();
    }

    private void initDialog() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "暂停", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //取消下载任务
                cancelable.cancel();
            }
        });
    }

    private void initData() {


        ll_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    //CliearUyils.getdqSize(ShezhiActivity.this);
                    String s = CliearUyils.getdqSize(ShezhiActivity.this);
                    tv_size.setText(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CliearUyils.clearAllCache(ShezhiActivity.this);
            }
        });
        sw_tuisong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    JPushInterface.resumePush(ShezhiActivity.this);
                }else {
                    JPushInterface.stopPush(ShezhiActivity.this);
                }
            }
        });
        ll_jiancha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager manager = getPackageManager();
                PackageInfo info = null;
                try {
                    info = manager.getPackageInfo(getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                int versionCode = info.versionCode;
                Version version = new Version();//自己创建的一个对象，里面封装了版本号和下载地址
                version.setUrl(url);
                if (versionCode < version.getVersionCode()) {
                    // File file = new File(Config.VERSION_PATH);
                    //System.out.println("file = " + file.getAbsolutePath());
                    AlertDialog.Builder ad = new AlertDialog.Builder(ShezhiActivity.this)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    downloadApk();
                                }
                            }).setNegativeButton("取消",null)
                            .setTitle("确定版本更新");
                    ad.show();

                }
            }
        });

    }

    private void downloadApk() {
        final RequestParams request = new RequestParams(url);
        request.setAutoResume(true);//设置是否支持断点下载
        request.setCancelFast(true);//设置是否立即取消
        //判断sdcard是否可用
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//判断sdcard存在并且可用

            request.setSaveFilePath(Config.VERSION_PATH);//自己创建的一个类，里面存放了下载路径public static final String VERSION_PATH = Environment.getExternalStorageDirectory()+"/danney/versions.apk";
        }

        cancelable = x.http().get(request, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {

                progressDialog.dismiss();

                System.out.println("filepath====" + result.getAbsolutePath());

                install(result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
                progressDialog.show();

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                if (isDownloading) {
                    progressDialog.setMax((int) total);
                    progressDialog.setProgress((int) current);
                    progressDialog.setTitle("下载进度");
                    System.out.println("current:" + (int) current * 100 / total);
                }
            }
        });
    }
    private void install(File result) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + result.getAbsolutePath()), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private void initView() {
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        ll_clear = (LinearLayout) findViewById(R.id.ll_clear);
        tv_size = (TextView) findViewById(R.id.tv_size);
        sw_tuisong = (Switch) findViewById(R.id.sw_tuisong);
        ll_jiancha = (LinearLayout) findViewById(R.id.ll_jiancha);
        tv_versioncode = (TextView) findViewById(R.id.tv_versioncode);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        try {
            String  s = CliearUyils.getdqSize(ShezhiActivity.this);
            tv_size.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
