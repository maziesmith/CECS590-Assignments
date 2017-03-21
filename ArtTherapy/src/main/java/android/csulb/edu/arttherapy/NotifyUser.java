package android.csulb.edu.arttherapy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by Sumeet on 3/19/2017.
 */

public class NotifyUser extends BroadcastReceiver {

    private Context context;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            Intent notifyIntent = new Intent(context,MainActivity.class);
            builder= new NotificationCompat.Builder(context).setContentTitle("Art Therapy").setContentText("Draw Art !!").
                    setAutoCancel(true).setSmallIcon(R.mipmap.ic_launcher);
            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
            taskStackBuilder.addParentStack(MainActivity.class);
            taskStackBuilder.addNextIntent(notifyIntent);
            PendingIntent resultPendingIntent =taskStackBuilder.getPendingIntent(
                                                0,
                                                PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(resultPendingIntent);
            notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, builder.build());
        }
    }
}
