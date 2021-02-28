package com.otnieldocs.uicontrol.recyclerview

import androidx.recyclerview.widget.DiffUtil

class DiffCallback<T> : DiffUtil.Callback() {
    private var oldList: List<T> = emptyList()
    private var newList: List<T> = emptyList()

    fun setList(oldList: List<T>, newList: List<T>) {
        this.oldList = oldList
        this.newList = newList
    }

    override fun areItemsTheSame(
        oldItemPosition: Int, newItemPosition: Int
    ): Boolean = oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(
        oldItemPosition: Int, newItemPosition: Int
    ): Boolean = oldList[oldItemPosition] === newList[newItemPosition]

}