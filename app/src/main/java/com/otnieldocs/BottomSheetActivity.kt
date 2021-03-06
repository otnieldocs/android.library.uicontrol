package com.otnieldocs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.otnieldocs.uicontrol.bottomsheet.AdjustableBottomSheetFragment
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import kotlinx.android.synthetic.main.main_bottom_sheet_option_fragment.view.*

class BottomSheetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)

        val bundle = Bundle().apply {
            putString("key1", "my key 1")
            putString("key2", "my key 2")
        }

        btn_option_bottom_sheet.setOnClickListener {
            AdjustableBottomSheetFragment.newInstance(
                bundle = bundle,
                layoutRes = R.layout.main_bottom_sheet_option_fragment,
                setupViews = ::setupBottomSheetOptionView
            ).show(supportFragmentManager, BottomSheetActivity::class.java.simpleName)
        }
    }

    private fun setupBottomSheetOptionView(view: View, bundle: Bundle?) {
        with(view) {
            bottom_sheet_option_item1.setOnClickListener {
                Toast.makeText(
                    this@BottomSheetActivity,
                    "${bundle?.getString("key1")}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            bottom_sheet_option_item2.setOnClickListener {
                Toast.makeText(
                    this@BottomSheetActivity,
                    "${bundle?.getString("key2")}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}