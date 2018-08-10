package linda.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;

import linda.notification.Monitor.Maps;


public class NotificationService extends NotificationListenerService {

    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public void onListenerConnected(){
        StatusBarNotification[] statusBarNotifications = getActiveNotifications ();

        for(StatusBarNotification notification : statusBarNotifications){
            identifyNotification(notification);
        }
    }
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        try {
            identifyNotification(sbn);
            String pack = sbn.getPackageName();
            Bundle extras = sbn.getNotification().extras;
            String title = extras.getCharSequence("android.title").toString();
            String text = extras.getCharSequence("android.text").toString();

            // Data Bundle attached with the broadcast
            Intent msgrcv = new Intent("Msg");
            msgrcv.putExtra("package", pack);
            msgrcv.putExtra("title", title);
            msgrcv.putExtra("text", text);

            LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);

        }catch (NullPointerException e){
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg","Notification Removed");
    }

    private void identifyNotification(StatusBarNotification notification){
        switch (notification.getPackageName()){
            case Maps.packageName: Maps.updateLastNotification(notification); break;
        }
    }
}
