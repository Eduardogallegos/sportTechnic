package mx.egs.sporttechnic

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.JobIntentService
import java.util.*


class NotificationService: JobIntentService() {
    private lateinit var mNotification: Notification
    private val JOB_ID = 1

    fun enqueueWork(context: Context, work: Intent) {
        enqueueWork(context, NotificationService::class.java, JOB_ID, work)
    }

    private fun createID(): Int {
        val ID : Int = System.currentTimeMillis().toInt()
        return ID
    }


    @SuppressLint("NewApi")
    private fun createChannel() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val context = this.applicationContext
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.enableLights(true)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }

    companion object {

        const val CHANNEL_ID = "mx.egs.sporttechnic.CHANNEL_ID"
        const val CHANNEL_NAME = "Reminder Notification"
    }


    override fun onHandleWork(intent: Intent) {
    //Create Channel
        createChannel()

        var timestamp: Long = 0
        if (intent.extras != null) {
            timestamp = intent.extras!!.getLong("timestamp")
        }

        if (timestamp > 0) {
            val context = this.applicationContext
            val notifyIntent = Intent(this, MenuActiv::class.java)

            val title = "Sport Technic Reminder"
            val message = "It is almost time for your training"

            notifyIntent.putExtra("title", title)
            notifyIntent.putExtra("message", message)
            notifyIntent.putExtra("notification", true)

            notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp

            val pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val res = this.resources
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mNotification = Notification.Builder(this, CHANNEL_ID)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.common_google_signin_btn_icon_light)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setStyle(Notification.BigTextStyle()
                        .bigText(message))
                    .setContentText(message).build()
            } else {
                mNotification = Notification.Builder(this)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.common_google_signin_btn_icon_light)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle(title)
                    .setStyle(Notification.BigTextStyle()
                        .bigText(message))
                    .setSound(uri)
                    .setContentText(message).build()

            }

            val mNotificationId = createID()
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // mNotificationId is a unique int for each notification that you must define
            notificationManager.notify(mNotificationId, mNotification)
        }

    }
}