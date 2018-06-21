package com.yanghaoyi.keepservice;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yanghaoyi.keepservice.broadcast.MyReceiver;
import com.yanghaoyi.keepservice.broadcast.NetworkReceiver;

public class MainActivity extends AppCompatActivity {

    private MyReceiver receiver;
    private NetworkReceiver networkChangedReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //生成一个自定义广播接收器对象
       receiver = new MyReceiver();
        //生成一个intent过滤器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.android.keep");
        //动态注册广播
        registerReceiver(receiver,intentFilter);

        networkChangedReceiver = new NetworkReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangedReceiver, filter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        unregisterReceiver(networkChangedReceiver);
    }
}
