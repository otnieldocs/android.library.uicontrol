package com.otnieldocs.uicontrol.edittext

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View.OnKeyListener
import androidx.appcompat.widget.AppCompatEditText

class AdjustableEditText : AppCompatEditText {

    @JvmOverloads
    constructor(context: Context) : super(context)

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    private var editTextConfig = EditTextConfigDto()
    private var editTextKeyListener: OnKeyListener? = null

    fun config(configuration: EditTextConfigDto): AdjustableEditText {
        editTextConfig = configuration.copy()

        return this
    }

    fun applyConfig() {
        with(editTextConfig) {
            if (activateKeyEvent != null) applyKeyEvent()
            if (activateValidationRule != null) applyValidationRule()
        }
    }

    private fun applyKeyEvent() {
        editTextKeyListener = OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP) {
                val handler = editTextConfig.activateKeyEvent?.get(keyCode)
                handler?.invoke()
                return@OnKeyListener handler != null
            }
            false
        }.also {
            setOnKeyListener(it)
        }
    }

    private fun applyValidationRule() {
        editTextConfig.activateValidationRule?.let { rule ->
            setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    rule.invoke()
                }
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        editTextKeyListener = null
        setOnKeyListener(editTextKeyListener)
    }
}