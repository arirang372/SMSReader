package com.sung.smsreaderdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony

/**
 *  A broadcast receiver that picks up the text message when the text message comes in.
 *
 */
class SmsListener : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent.action) {
            for (message in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                val messageBody = message.messageBody

                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("text_message", messageBody)
                context.startActivity(intent)
            }
        }
    }
}