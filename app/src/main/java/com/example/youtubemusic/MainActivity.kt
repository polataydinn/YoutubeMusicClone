package com.example.youtubemusic

import android.Manifest
import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View.INVISIBLE
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.example.youtubemusic.interfaces.PassDataInterface
import com.example.youtubemusic.models.Item
import com.example.youtubemusic.ui.search.SearchFragmentDirections
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.sql.Time
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class MainActivity : AppCompatActivity(), PassDataInterface {

    var listOfSongs: List<Item>? = null
    var position : Int? = null
    var songLenght : String? = null
    private var item: Item? = null
    var player: MediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        setUserPermission()
        val bottomPlayer: RelativeLayout = findViewById(R.id.bottomPlayerController)
        bottomPlayer.visibility = INVISIBLE

        bottomPlayer.setOnClickListener {
            bottomPlayer.visibility = INVISIBLE
            item?.let { it1 ->
                SearchFragmentDirections.actionNavigationSearchToNavigationPlaySong(
                    it1
                )
            }?.let { it2 -> navController.navigate(it2) }
        }
    }

    private fun setUserPermission() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                    }

                    if (report.isAnyPermissionPermanentlyDenied) {
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {
                Toast.makeText(
                    baseContext,
                    "Error occurred! ",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .onSameThread()
            .check()
    }


    private fun showSettingsDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(baseContext)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton(
            "GOTO SETTINGS"
        ) { dialog, which ->
            dialog.cancel()
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, which -> dialog.cancel() }
        builder.show()
    }

    override fun onDataRecieved(item: Item) {
        this.item = item
    }
}