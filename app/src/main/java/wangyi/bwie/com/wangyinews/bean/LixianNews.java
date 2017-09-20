package wangyi.bwie.com.wangyinews.bean;

/**
 * 作者： 张少丹
 * 时间：  2017/9/17.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class LixianNews {
    private String type;
    private String content;

    public LixianNews(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public LixianNews() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LixianNews{" +
                "type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
