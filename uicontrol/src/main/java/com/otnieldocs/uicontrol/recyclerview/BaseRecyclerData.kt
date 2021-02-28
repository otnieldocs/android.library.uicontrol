package com.otnieldocs.uicontrol.recyclerview

open class BaseRecyclerData {
    inline fun <reified T : BaseRecyclerData> cast(): T {
        return this as T
    }
}