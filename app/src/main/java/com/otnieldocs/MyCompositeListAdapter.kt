package com.otnieldocs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.otnieldocs.uicontrol.recyclerview.BaseRecyclerData
import com.otnieldocs.uicontrol.recyclerview.BaseCompositeRecyclerViewHolder
import com.otnieldocs.uicontrol.recyclerview.CompositeRecyclerAdapter

class MyCompositeListAdapter : CompositeRecyclerAdapter<BaseRecyclerData>() {
    override fun getDataType(data: BaseRecyclerData): Int {
        return when(data) {
            is TeacherDto -> TEACHER
            else -> CAR
        }
    }

    override fun getViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseCompositeRecyclerViewHolder<BaseRecyclerData> {
        return when(viewType) {
            TEACHER -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.main_item_teacher, parent, false)
                TeacherViewHolder(view)
            }
            else -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.main_item_car, parent, false)
                CarViewHolder(view)
            }
        }
    }

    companion object {
        const val TEACHER = 0
        const val CAR = 1
    }
}