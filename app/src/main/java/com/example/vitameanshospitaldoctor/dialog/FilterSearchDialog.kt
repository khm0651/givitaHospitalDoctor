package com.example.vitameanshospitaldoctor.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.fragment.app.DialogFragment
import com.example.vitameanshospitaldoctor.databinding.FilterSearchDialogBinding

class FilterSearchDialog : DialogFragment() {
    private var _binding: FilterSearchDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FilterSearchDialogBinding.inflate(inflater,container,false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        binding.ivCancel.setOnClickListener {
            dismiss()
        }

        return view
    }
    override fun onResume() {
        super.onResume()
        val windowManager = requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params?.width = (size.x * 0.9).toInt()
        params?.height = (size.y * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

}