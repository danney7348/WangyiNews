package wangyi.bwie.com.wangyinews.bean;

/**
 * 作者： 张少丹
 * 时间：  2017/9/15.
 * 邮箱：1455456581@qq.com
 * 类的用途：
 */

public class Popup {
    private int img;
    private String name;

    public Popup( String name,int img) {

        this.img = img;
        this.name = name;
    }

    public Popup() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Popup{" +
                "img=" + img +
                ", name='" + name + '\'' +
                '}';
    }
}
