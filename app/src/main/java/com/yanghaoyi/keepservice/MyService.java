package com.yanghaoyi.keepservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author : YangHaoYi on 2018/6/20.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/20.
 *         Version : V 1.0
 */
public class MyService extends Service{

    private Timer timer;
    private CheckMainAppTask checkMainAppTask;

    @Override
    public void onCreate() {
        super.onCreate();
        startTimer();
        System.out.println("MyService__________________________________________Create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "保活 已经唤醒", Toast.LENGTH_SHORT).show();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    private void startTimer(){
        if(null==timer){
            timer = new Timer();
            checkMainAppTask = new CheckMainAppTask();
            timer.schedule(checkMainAppTask,0,1000);
            System.out.println("MyService__________________________________________Timer____start");
        }else {
            System.out.println("MyService__________________________________________Timer____already_start");
        }
    }


    private void cancelTimer(){
        if(null!=timer){
            timer.cancel();
            timer = null;
        }
        if(null!=checkMainAppTask){
            checkMainAppTask.cancel();
            checkMainAppTask = null;
            System.out.println("MyService__________________________________________Timer____cancel");
        }
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class CheckMainAppTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("MyService__________________________________________Run");
            Intent intent = new Intent();
            intent.setAction("com.android.keep");
            sendBroadcast(intent);
        }
    }

}
