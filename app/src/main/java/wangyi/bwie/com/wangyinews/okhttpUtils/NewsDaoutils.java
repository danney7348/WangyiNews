package wangyi.bwie.com.wangyinews.okhttpUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import wangyi.bwie.com.wangyinews.bean.News;
import wangyi.bwie.com.wangyinews.bean.NewsListInterface;

/**
 * 作者： 张少丹
 * 时间：  2017/9/14.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class NewsDaoutils {

    private NewsListInterface newsListInterface;

    public void setNewsListInterface(NewsListInterface newsListInterface) {
        this.newsListInterface = newsListInterface;
    }

    public void getList(String url){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                newsListInterface.onNewsListFailure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    String result = response.body().string();
                    System.out.println("result =========== " + result);
                    Gson gson = new Gson();
                    News news = gson.fromJson(result, News.class);
                    List<News.ResultBean.DataBean> data = news.getResult().getData();
                    newsListInterface.onNewsListResponse(call,data);
                }
            }
        });
    }
}
