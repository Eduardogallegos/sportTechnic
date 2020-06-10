package mx.egs.sporttechnic

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import java.util.*

class NotificationUtils {

    fun setNotification(timeInMilliSeconds: Long, time: String, date : String, activity:Activity){

        if (timeInMilliSeconds > 0){

            val alarmManager = activity.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(activity.applicationContext, AlarmReceiver::class.java)

            alarmIntent.putExtra("reason", "notification")
            alarmIntent.putExtra("timestamp", timeInMilliSeconds)

            val strDate = date.split("/").toTypedArray()
            val strTime = time.split(":").toTypedArray()

            val dia = strDate[0].toInt()
            val mes = strDate[1].toInt()
            val anio = strDate[2].toInt()

            val hora = strTime[0].toInt()
            val minutos = strTime[1].toInt()

            Log.d("sports technic time", "La fecha es $dia, $hora, $minutos")

            val calendar: Calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.DAY_OF_MONTH, dia)
                set(Calendar.MONTH, mes)
                set(Calendar.YEAR, anio)
                set(Calendar.HOUR_OF_DAY, hora)
                set(Calendar.MINUTE, minutos)
                set(Calendar.SECOND, 0)
            }

            Log.d("sports technic time", "Calendario es ${calendar.timeInMillis}")

            val pendingIntent = PendingIntent.getBroadcast(activity, 0, alarmIntent,
                PendingIntent.FLAG_CANCEL_CURRENT)

            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
    }
}