package com.otnieldocs

import android.view.View
import com.otnieldocs.uicontrol.recyclerview.BaseCompositeRecyclerViewHolder
import com.otnieldocs.uicontrol.recyclerview.BaseRecyclerData
import kotlinx.android.synthetic.main.main_item_car.view.*

class CarViewHolder(itemView: View): BaseCompositeRecyclerViewHolder<CarDto>(itemView) {

    override fun bind(item: BaseRecyclerData, position: Int) {
        val carDto = item.cast<CarDto>()

        with(itemView) {
            main_textview_car_itemname.text = carDto.name
            main_textview_car_itempolicenumber.text = carDto.policeNumber
        }
    }
}