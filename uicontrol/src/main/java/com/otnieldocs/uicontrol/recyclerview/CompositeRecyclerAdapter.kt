package com.otnieldocs.uicontrol.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class CompositeRecyclerAdapter<T : BaseRecyclerData> :
    RecyclerView.Adapter<BaseCompositeRecyclerViewHolder<T>>() {
    private var adapterDataList = mutableListOf<T>()
    private val diffCallback =
        DiffCallback<T>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseCompositeRecyclerViewHolder<T> {
        return getViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int = adapterDataList.size

    override fun onBindViewHolder(
        holder: BaseCompositeRecyclerViewHolder<T>,
        position: Int
    ) {
        val item = adapterDataList[position]
        holder.bind(item, position)
    }

    override fun getItemViewType(position: Int): Int {
        val data = adapterDataList[position]

        return getDataType(data)
    }

    abstract fun getDataType(data: T): Int

    abstract fun getViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseCompositeRecyclerViewHolder<T>

    fun setData(newData: List<T>) {
        calculateDiff(newData)
    }

    fun addData(newData: List<T>) {
        calculateDiff(adapterDataList.apply {
            addAll(newData)
        })
    }

    private fun calculateDiff(newList: List<T>) {
        diffCallback.setList(adapterDataList, newList)
        val result = DiffUtil.calculateDiff(diffCallback)
        with(adapterDataList) {
            clear()
            addAll(newList)
        }
        result.dispatchUpdatesTo(this)
    }
}