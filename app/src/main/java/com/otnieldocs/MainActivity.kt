package com.otnieldocs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_composite_recyclerview.setOnClickListener {
            startActivity(Intent(this, CompositeRecyclerViewActivity::class.java))
        }

        btn_form_utilities.setOnClickListener {
            startActivity(Intent(this, FormControlActivity::class.java))
        }

        btn_option_bottom_sheet.setOnClickListener {
            startActivity(Intent(this, BottomSheetActivity::class.java))
        }
    }
}