package com.example.vitameanshospitaldoctor.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vitameanshospitaldoctor.databinding.EmrRegistrationDialogBinding
import com.example.vitameanshospitaldoctor.databinding.NameSearchDialogBinding

class EmrRegistrationDialog(val count : Int) : DialogFragment() {

    lateinit var binding: EmrRegistrationDialogBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmrRegistrationDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.apply {
            ivCancel.setOnClickListener {
                dismiss()
            }

            linearBottom.setOnClickListener {
                dismiss()
            }

            tvResult.text = "선택한 ${count}명의 환자 중 기록되지 않은 혈압 혈당 정보를 가진"
            tvResult2.text= "${count}명의 기록 21건을 EMR로 전송하였습니다."

        }


        return view
    }

    override fun onResume() {
        super.onResume()
        val windowManager =
            requireActivity().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        params?.width = (size.x * 0.9).toInt()
        params?.height = (size.y * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }



}