package conall.ucc.clockapp;

import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;


public class RingtoneNotificationService extends Service
{
    private Ringtone ringtone;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }


    // when service is stared gives a notification which allows the user to dismiss the alarm

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (ringtoneUri == null)
        {
            ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        this.ringtone = RingtoneManager.getRingtone(this, ringtoneUri);
        ringtone.play();

        NotificationMaker notification = new NotificationMaker(this);
        notification.createNotification("Your Alarm is Ringing!", "Click to Dismiss!");

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        ringtone.stop();
    }
}