package com.example.android.calculatorrpn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    override fun finish() {
        val data = Intent()
        var color = android.graphics.Color.GREEN


        when (true) {
            yellow.isChecked -> color = android.graphics.Color.YELLOW
            green.isChecked -> color = android.graphics.Color.GREEN
            white.isChecked -> color = android.graphics.Color.WHITE
            red.isChecked -> color = android.graphics.Color.RED
            else -> { // Note the block
                color = android.graphics.Color.RED
            }
        }
        data.putExtra("returnString1", color)
        setResult(Activity.RESULT_OK,data)
        super.finish()
    }
}
