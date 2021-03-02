package com.otnieldocs

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.otnieldocs.uicontrol.edittext.EditTextConfigDto
import kotlinx.android.synthetic.main.activity_form_control.*

class FormControlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_control)

        adjustable_edit_text1.config(
            EditTextConfigDto(
                activateKeyEvent = mapOf(
                    KeyEvent.KEYCODE_ENTER to onEnterKeyPressed
                ),
                activateValidationRule = validateTextNotEmpty
            )
        ).applyConfig()

        adjustable_edit_text2.config(
            EditTextConfigDto(
                activateKeyEvent = mapOf(
                    KeyEvent.KEYCODE_ENTER to onEnterKeyPressed
                ),
                activateValidationRule = validateTextNotEmpty
            )
        )
    }

    private val onEnterKeyPressed: () -> Unit = {
        Toast.makeText(this, "Enter key pressed", Toast.LENGTH_SHORT).show()
    }

    private val validateTextNotEmpty: () -> Unit = {
        when {
            adjustable_edit_text1.text.toString().isEmpty() -> {
                Toast.makeText(
                    this,
                    "This value can't be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
            adjustable_edit_text1.text.toString().length < 3 -> {
                Toast.makeText(
                    this,
                    "This value have to be larger or equals than 3",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                Toast.makeText(
                    this,
                    "This value is valid",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}