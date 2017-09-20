package com.Moyuchen.networjudement;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * User: 张亚博
 * Date: 2017-09-17 13:47
 * Description：
 */
public class NetWorkjudeUtils {
//    private networkjude networkjude;

//    public void setNetworkjude(NetWorkjudeUtils.networkjude networkjude) {
//        this.networkjude = networkjude;
//    }

    public static void judement(Context context,networkjude networkjude){
        ConnectivityManager conmanager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = conmanager.getActiveNetworkInfo();
        if (activeNetworkInfo==null) {
            networkjude.UNnetwork();
        }else{
            if (activeNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE) {
                networkjude.Mobilenetwork();
            }else if (activeNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI) {
                networkjude.Wifinetwork();

            }else{
                networkjude.UNnetwork();
            }
        }



    }
    public interface networkjude{
        void Mobilenetwork();
        void Wifinetwork();
        void UNnetwork();
    }
}
