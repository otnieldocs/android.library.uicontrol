package com.otnieldocs

import android.view.View
import com.otnieldocs.uicontrol.recyclerview.BaseCompositeRecyclerViewHolder
import com.otnieldocs.uicontrol.recyclerview.BaseRecyclerData
import kotlinx.android.synthetic.main.main_item_teacher.view.*

class TeacherViewHolder(itemView: View) : BaseCompositeRecyclerViewHolder<CarDto>(itemView) {
    override fun bind(item: BaseRecyclerData, position: Int) {
        val teacherDto = item.cast<TeacherDto>()

        with(itemView) {
            main_textview_teacher_itemname.text = teacherDto.name
        }
    }

}