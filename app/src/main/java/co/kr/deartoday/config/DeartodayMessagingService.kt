package co.kr.deartoday.config

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import co.kr.deartoday.R
import co.kr.deartoday.data.sharedpreferences.DearTodaySharedPreferences
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class DeartodayMessagingService : FirebaseMessagingService() {
    @Inject
    lateinit var sharedPreferences: DearTodaySharedPreferences

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sharedPreferences.deviceToken = token
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.data.isNotEmpty()) {
            for ((key, value) in message.data) {
                Timber.d("Deartoday message key $key value $value")
            }
            sendNotificationAlarm(
                Message(message.data["title"].toString(), message.data["content"].toString())
            )
        } else {
            message.notification?.let {
                Timber.d("Deartoday message key ${it.title} value $${it.body}")
                sendNotificationAlarm(Message(it.title.toString(), it.body.toString()))
            }
        }
    }

    private fun sendNotificationAlarm(message: Message) {
        val channelId = getString(R.string.default_notification_channel_id)

        val notificationBuilder =
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(message.title)
                .setContentText(message.body)
                .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            channelId, channelId, NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.run {
            createNotificationChannel(channel)
        }
        with(NotificationManagerCompat.from(this)) {
            notify(1000, notificationBuilder.build())
        }
    }

    private data class Message(val title: String, val body: String)
}