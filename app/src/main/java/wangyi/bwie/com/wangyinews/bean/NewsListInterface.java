package wangyi.bwie.com.wangyinews.bean;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * 作者： 张少丹
 * 时间：  2017/9/14.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public interface NewsListInterface {
    void onNewsListFailure(Call call, IOException e);//失败的回调
    void onNewsListResponse(Call call, List<News.ResultBean.DataBean> list);
}
