@file:Suppress("PrivatePropertyName")

package com.sung.smsreaderdemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val SMS_READ_PERMISSION_REQUEST_CODE = 100
    private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var progressBarMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.verificationCode)
        progressBar = findViewById(R.id.progressBar)
        progressBarMessage = findViewById(R.id.progressBarMessage)

        var argument = intent.getStringExtra("text_message")
        textView.text = argument ?: ""
        if (!hasPermission()) {
            setProgressBarVisibleInvisible(false)
            requestPermissions(arrayOf(Manifest.permission.RECEIVE_SMS),
                SMS_READ_PERMISSION_REQUEST_CODE)
        } else {
            setProgressBarVisibleInvisible(true)
        }
    }

    private fun hasPermission() =
        ContextCompat.checkSelfPermission(this,
            Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_READ_PERMISSION_REQUEST_CODE) {
            setProgressBarVisibleInvisible(true)
        }
    }

    private fun setProgressBarVisibleInvisible(visible: Boolean) {
        if (visible) {
            progressBar.visibility = VISIBLE
            progressBarMessage.visibility = VISIBLE
        } else {
            progressBar.visibility = GONE
            progressBarMessage.visibility = GONE
        }
    }
}