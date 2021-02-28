package com.otnieldocs.uicontrol.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseCompositeRecyclerViewHolder<out T : BaseRecyclerData>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: BaseRecyclerData, position: Int)
}