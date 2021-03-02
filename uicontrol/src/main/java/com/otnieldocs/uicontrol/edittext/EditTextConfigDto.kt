package com.otnieldocs.uicontrol.edittext

data class EditTextConfigDto(
    val activateValidationRule: (() -> Unit)? = null,
    val activateKeyEvent: Map<Int, (() -> Unit)?>? = null
)