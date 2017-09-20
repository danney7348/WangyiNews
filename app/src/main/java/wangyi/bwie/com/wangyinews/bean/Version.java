package wangyi.bwie.com.wangyinews.bean;

/**
 * 作者： 张少丹
 * 时间：  2017/9/16.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class Version {
    private int versionCode = 2;
    private String url;

    public Version() {
    }

    public Version(int versionCode, String url) {
        this.versionCode = versionCode;
        this.url = url;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Version{" +
                "versionCode=" + versionCode +
                ", url='" + url + '\'' +
                '}';
    }
}
