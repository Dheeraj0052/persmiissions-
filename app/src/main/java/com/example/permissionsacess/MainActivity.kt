package com.example.permissionsacess

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.telecom.Call
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    lateinit var callIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCall.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val input = "tel:${edtNuumber.text}"
                callIntent = Intent(Intent.ACTION_CALL).apply {
                    data = Uri.parse(input)
                    if(ActivityCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.CALL_PHONE
                        )==PackageManager.PERMISSION_GRANTED){
                        startActivity(callIntent)
                    }
                    else{

                        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.CALL_PHONE),1234)
                    }
                }

            }
        })

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==1234)
        { if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
         startActivity(callIntent)
        }
            else{
            Toast.makeText(baseContext,"permission needed ",Toast.LENGTH_SHORT).show()
        }

        }
    }
}
