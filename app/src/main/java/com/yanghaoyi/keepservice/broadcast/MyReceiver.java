package com.yanghaoyi.keepservice.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Date;

/**
 * @author : YangHaoYi on 2018/6/20.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/20.
 *         Version : V 1.0
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("MyReceiver_______________________________________________"+ new Date());
    }
}
