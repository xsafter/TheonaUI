package com.example.theonaui2.ui.main.chat

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.theonaui2.R
import com.example.theonaui2.ui.main.Chat

class ChatActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setContent {
            Chat()
        }
    }
//    fun galleryOnClick() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
//                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
//                requestPermissions(permissions, PERMISSION_CODE)
//            } else{
//                chooseImageGallery();
//            }
//        }else{
//            chooseImageGallery();
//        }
//    }
//
//    companion object {
//        private val IMAGE_CHOOSE = 1000;
//        private val PERMISSION_CODE = 1001;
//        private const val REQUEST_CODE = 13
//    }
//
//
//    private fun chooseImageGallery() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, IMAGE_CHOOSE)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        when(requestCode){
//            PERMISSION_CODE -> {
//                if (grantResults.size > 0 && grantResults[0] ==
//                    PackageManager.PERMISSION_GRANTED){
//                    //permission from popup was granted
//                    openCamera()
//                }
//                else{
//                    //permission from popup was denied
//                    toast("Permission denied")
//                }
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode == Companion.REQUEST_CODE && resultCode == Activity.RESULT_OK){
//            //viewImage.setImageURI(data?.data) TODO: add handler to send this image. Depends on backend
//        } else if (requestCode == IMAGE_CHOOSE && resultCode == Activity.RESULT_OK){
//            //viewImage.setImageURI(data?.data) TODO: add handler to send this image. Depends on backend
//        }
//    }
}