package com.r2devpros.serverconnection

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnValidateConnection).setOnClickListener {

            // Google Server
            findViewById<TextView>(R.id.tvServerGoogleStatus).text =
                if (isConnectedToInternet(googleServer)) getString(R.string.txt_connected) else getString(
                    R.string.txt_failed
                )

            //Engage360
            findViewById<TextView>(R.id.tvServerEngageStatus).text =
                if (isConnectedToInternet(engage360)) getString(R.string.txt_connected) else getString(
                    R.string.txt_failed
                )

            //Engage360 media
            findViewById<TextView>(R.id.tvServerEngageMediaStatus).text =
                if (isConnectedToInternet(engageMediaServer)) getString(R.string.txt_connected) else getString(
                    R.string.txt_failed
                )
        }
    }

    companion object {
        const val googleServer = "8.8.8.8"
        const val engage360 = "dev.www.engage360.evacompute.com"
        const val engageMediaServer = "meta.engage360.evacompute.com"
    }

    // Google Server: 8.8.8.8
    // Engage360: www.engage360.evacompute.com:8080/ [23.21.105.150]
    // Engage360 - Media: http://meta.engage360.evacompute.com/ [44.193.186.189]

    private fun isConnectedToInternet(serverHost: String): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 $serverHost")
            val exitValue = ipProcess.waitFor()
            val response = (exitValue == 0)
            Log.d(
                "MainActivity_TAG",
                "IsConnectedToInternet: Server: $serverHost Connected: $response"
            )
            return response
        } catch (e: Exception) {
            Log.d("MainActivity_TAG", "IsConnectedToInternet: Connected:$e")
        }
        return false
    }
}