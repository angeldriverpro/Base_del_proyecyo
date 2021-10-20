package com.example.prueba_map

import android.util.Log
import android.widget.Toast
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage

class MyHmsMessageService : HmsMessageService() {
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        token?.let {
            Log.i("TOKEN",it)

            Toast.makeText(this,  it , Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
    }
}