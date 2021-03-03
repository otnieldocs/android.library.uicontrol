package com.otnieldocs.uicontrol.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AdjustableBottomSheetFragment(
    @LayoutRes private val layoutRes: Int,
    private val setupViews: (View, Bundle?) -> Unit
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews.invoke(view, arguments)
    }

    companion object {
        @JvmStatic
        fun newInstance(
            bundle: Bundle?,
            layoutRes: Int,
            setupViews: (View, Bundle?) -> Unit
        ): AdjustableBottomSheetFragment {
            return AdjustableBottomSheetFragment(layoutRes, setupViews).apply {
                arguments = bundle
            }
        }
    }
}