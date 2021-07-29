package com.example.youtubemusic

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri

class DownloadBroadCastReceiver(val downloadId: Long, val onComplete: (Uri) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)  ?: -1L
        if (downloadId == id) {
            val downloadManager =
                context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val uri = downloadManager.getUriForDownloadedFile(downloadId)
            onComplete(uri)

        }
    }
}


