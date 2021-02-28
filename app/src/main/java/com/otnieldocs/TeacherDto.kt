package com.otnieldocs

import com.otnieldocs.uicontrol.recyclerview.BaseRecyclerData

data class TeacherDto(
    val name: String = "",
    val teacherId: String = ""
) : BaseRecyclerData()