package com.azhar.alquran.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.azhar.alquran.R
import com.azhar.alquran.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        bottomNavigationView.setOnNavigationItemSelectedListener {

            when(it.itemId){

                R.id.about -> startActivity(Intent(this@MainActivity, AboutActivity::class.java))
                else -> {}
            }
            true
        }

        linearNiatShalat.setOnClickListener(this)
        linearAlquran.setOnClickListener (this)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.linearNiatShalat -> {
                val intentNiat = Intent(this@MainActivity, NiatShalatActivity::class.java)
                startActivity(intentNiat)
            }
            R.id.linearAlquran -> {
                val intentAlquran = Intent(this@MainActivity, ListSurahActivity::class.java)
                startActivity(intentAlquran)
            }

        }
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val activityWindow = activity.window
            val layoutParams = activityWindow.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            activityWindow.attributes = layoutParams
        }
    }

}