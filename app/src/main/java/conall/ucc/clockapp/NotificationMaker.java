package conall.ucc.clockapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

public class NotificationMaker {


    private Context context;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    public NotificationMaker(Context context) {
        this.context = context;
    }

    public void createNotification(String title, String message)
    {
        Intent resultIntent = new Intent(context, AlarmActivity.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,
                0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.baseline_alarm_black_18dp)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setContentIntent(resultPendingIntent)

                .setAutoCancel(true);


        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        notificationManager.createNotificationChannel(notificationChannel);

        builder.build().flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, builder.build());


    }
}
