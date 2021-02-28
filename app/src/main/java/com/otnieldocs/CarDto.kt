package com.otnieldocs

import com.otnieldocs.uicontrol.recyclerview.BaseRecyclerData

data class CarDto(
    val name: String = "",
    val policeNumber: String = ""
) : BaseRecyclerData()