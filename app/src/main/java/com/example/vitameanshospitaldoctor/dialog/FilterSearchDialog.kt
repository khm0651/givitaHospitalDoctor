package com.example.vitameanshospitaldoctor.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.drawerlayout.R
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.liveData
import com.example.vitameanshospitaldoctor.data.entities.UserData
import com.example.vitameanshospitaldoctor.databinding.FilterSearchDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterSearchDialog : DialogFragment() {
    lateinit var binding: FilterSearchDialogBinding
    private val viewModel: FilterSearchDialogVM by viewModels()

    interface btnClickListener{
        fun onClicked(userData: List<UserData>?)
    }
    private lateinit var onClickedListener:btnClickListener

    fun setOnClickedListener(listener: btnClickListener){
        onClickedListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FilterSearchDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        age()
        gender()
        diseaseType()
        bpShrin()
        bpRelax()
        visitDate()
        receive()
        viewModel.result.observe(viewLifecycleOwner,{
            onClickedListener.onClicked(it)

        })
        binding.apply {
            ivCancel.setOnClickListener {
                dismiss()
            }
            linearBottom.setOnClickListener {
                viewModel.minAge.value=etInsertMinAge.text.toString().toInt()
                viewModel.maxAge.value=etInsertMaxAge.text.toString().toInt()
                viewModel.gender.value=etInsertSex.text.toString()
                viewModel.type.value=etInsertDiseaseType.text.toString()
                viewModel.minBpShrinkage.value=etInsertMinBpShrinkage.text.toString().toInt()
                viewModel.maxBpShrinkage.value=etInsertMaxBpShrinkage.text.toString().toInt()
                viewModel.minBpRelaxation.value=etInsertMinBpRelaxation.text.toString().toInt()
                viewModel.maxBpRelaxation.value=etInsertMaxBpRelaxation.text.toString().toInt()
                viewModel.visitDate.value=etInsertVisitDate.text.toString()
                viewModel.receive.value=etInsertReceive.text.toString()
                viewModel.search()
                dismiss()
            }
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


    private fun age() {
        binding.apply {
            fun text() {
                tv10age.setTextColor(Color.parseColor("#BCBCBC"))
                tv20age.setTextColor(Color.parseColor("#BCBCBC"))
                tv30age.setTextColor(Color.parseColor("#BCBCBC"))
                tv40age.setTextColor(Color.parseColor("#BCBCBC"))
                tv50age.setTextColor(Color.parseColor("#BCBCBC"))
                tv60age.setTextColor(Color.parseColor("#BCBCBC"))
            }
            tv10age.setOnClickListener {
                text()
                etInsertMinAge.setText("10")
                etInsertMaxAge.setText("19")
                tv10age.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tv20age.setOnClickListener {
                text()
                etInsertMinAge.setText("20")
                etInsertMaxAge.setText("29")
                tv20age.setTextColor(Color.parseColor("#4B4B4B"))

            }
            tv30age.setOnClickListener {
                text()
                etInsertMinAge.setText("30")
                etInsertMaxAge.setText("39")
                tv30age.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tv40age.setOnClickListener {
                text()
                etInsertMinAge.setText("40")
                etInsertMaxAge.setText("49")
                tv40age.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tv50age.setOnClickListener {
                text()
                etInsertMinAge.setText("50")
                etInsertMaxAge.setText("59")
                tv50age.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tv60age.setOnClickListener {
                text()
                etInsertMinAge.setText("60")
                etInsertMaxAge.setText("150")
                tv60age.setTextColor(Color.parseColor("#4B4B4B"))
            }
            etInsertMinAge.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    text()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

            })
            etInsertMaxAge.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    text()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })
        }
    }

    private fun gender() {
        var male = false
        var female = false


        binding.apply {

            fun text() {
                if (male && female) {
                    etInsertSex.setText("모두")
                } else if (male && !female) {
                    etInsertSex.setText("남성")
                } else if (!male && female) {
                    etInsertSex.setText("여성")
                } else {
                    etInsertSex.setText("모두")
                }
            }

            tvMale.setOnClickListener {
                male = !male
                if (male) {
                    tvMale.setTextColor(Color.parseColor("#4B4B4B"))
                } else {
                    tvMale.setTextColor(Color.parseColor("#BCBCBC"))
                }
                text()
            }
            tvFemale.setOnClickListener {
                female = !female
                if (female) {
                    tvFemale.setTextColor(Color.parseColor("#4B4B4B"))
                } else {
                    tvFemale.setTextColor(Color.parseColor("#BCBCBC"))
                }
                text()
            }

        }
    }

    private fun diseaseType() {
        var type1 = false
        var type2 = false


        binding.apply {

            fun text() {
                if (type1 && type2) {
                    etInsertDiseaseType.setText("모두")
                } else if (type1 && !type2) {
                    etInsertDiseaseType.setText("1형")
                } else if (!type1 && type2) {
                    etInsertDiseaseType.setText("2형")
                } else {
                    etInsertDiseaseType.setText("모두")
                }
            }

            tvType1.setOnClickListener {
                type1 = !type1
                if (type1) {
                    tvType1.setTextColor(Color.parseColor("#4B4B4B"))
                } else {
                    tvType1.setTextColor(Color.parseColor("#BCBCBC"))
                }
                text()
            }
            tvType2.setOnClickListener {
                type2 = !type2
                if (type2) {
                    tvType2.setTextColor(Color.parseColor("#4B4B4B"))
                } else {
                    tvType2.setTextColor(Color.parseColor("#BCBCBC"))
                }
                text()
            }

        }
    }

    private fun bpShrin() {
        binding.apply {
            fun text() {
                tvShrinLow.setTextColor(Color.parseColor("#BCBCBC"))
                tvShrinLowDanger.setTextColor(Color.parseColor("#BCBCBC"))
                tvShrinNormal.setTextColor(Color.parseColor("#BCBCBC"))
                tvShrinHighDanger.setTextColor(Color.parseColor("#BCBCBC"))
                tvShrinHigh.setTextColor(Color.parseColor("#BCBCBC"))
            }
            tvShrinLow.setOnClickListener {
                text()
                etInsertMinBpShrinkage.setText("1")
                etInsertMaxBpShrinkage.setText("90")
                tvShrinLow.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tvShrinLowDanger.setOnClickListener {
                text()
                etInsertMinBpShrinkage.setText("91")
                etInsertMaxBpShrinkage.setText("100")
                tvShrinLowDanger.setTextColor(Color.parseColor("#4B4B4B"))

            }
            tvShrinNormal.setOnClickListener {
                text()
                etInsertMinBpShrinkage.setText("101")
                etInsertMaxBpShrinkage.setText("120")
                tvShrinNormal.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tvShrinHighDanger.setOnClickListener {
                text()
                etInsertMinBpShrinkage.setText("121")
                etInsertMaxBpShrinkage.setText("139")
                tvShrinHighDanger.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tvShrinHigh.setOnClickListener {
                text()
                etInsertMinBpShrinkage.setText("140")
                etInsertMaxBpShrinkage.setText("300")
                tvShrinHigh.setTextColor(Color.parseColor("#4B4B4B"))
            }

            etInsertMinBpShrinkage.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    text()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

            })
            etInsertMaxBpShrinkage.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    text()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })
        }

    }

    private fun bpRelax() {
        binding.apply {
            fun text() {
                tvRelaxLow.setTextColor(Color.parseColor("#BCBCBC"))
                tvRelaxLowDanger.setTextColor(Color.parseColor("#BCBCBC"))
                tvRelaxNormal.setTextColor(Color.parseColor("#BCBCBC"))
                tvRelaxHighDanger.setTextColor(Color.parseColor("#BCBCBC"))
                tvRelaxHigh.setTextColor(Color.parseColor("#BCBCBC"))
            }
            tvRelaxLow.setOnClickListener {
                text()
                etInsertMinBpRelaxation.setText("1")
                etInsertMaxBpRelaxation.setText("60")
                tvRelaxLow.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tvRelaxLowDanger.setOnClickListener {
                text()
                etInsertMinBpRelaxation.setText("61")
                etInsertMaxBpRelaxation.setText("70")
                tvRelaxLowDanger.setTextColor(Color.parseColor("#4B4B4B"))

            }
            tvRelaxNormal.setOnClickListener {
                text()
                etInsertMinBpRelaxation.setText("71")
                etInsertMaxBpRelaxation.setText("80")
                tvRelaxNormal.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tvRelaxHighDanger.setOnClickListener {
                text()
                etInsertMinBpRelaxation.setText("81")
                etInsertMaxBpRelaxation.setText("89")
                tvRelaxHighDanger.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tvRelaxHigh.setOnClickListener {
                text()
                etInsertMinBpRelaxation.setText("90")
                etInsertMaxBpRelaxation.setText("300")
                tvRelaxHigh.setTextColor(Color.parseColor("#4B4B4B"))
            }

            etInsertMinBpRelaxation.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    text()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

            })
            etInsertMaxBpRelaxation.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    text()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })
        }

    }

    private fun visitDate() {

        binding.apply {

            fun text() {
                tv1week1.setTextColor(Color.parseColor("#BCBCBC"))
                tv1week2.setTextColor(Color.parseColor("#BCBCBC"))
                tv1month1.setTextColor(Color.parseColor("#BCBCBC"))
                tv1month2.setTextColor(Color.parseColor("#BCBCBC"))
                tv3month1.setTextColor(Color.parseColor("#BCBCBC"))
                tv3month2.setTextColor(Color.parseColor("#BCBCBC"))
                tv6month1.setTextColor(Color.parseColor("#BCBCBC"))
                tv6month2.setTextColor(Color.parseColor("#BCBCBC"))
                tvDateSetting1.setTextColor(Color.parseColor("#BCBCBC"))
                tvDateSetting2.setTextColor(Color.parseColor("#BCBCBC"))
            }

            tv1week.setOnClickListener {
                text()
                etInsertVisitDate.setText("1주일")
                tv1week1.setTextColor(Color.parseColor("#4B4B4B"))
                tv1week2.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tv1month.setOnClickListener {
                text()
                etInsertVisitDate.setText("1개월")
                tv1month1.setTextColor(Color.parseColor("#4B4B4B"))
                tv1month2.setTextColor(Color.parseColor("#4B4B4B"))

            }
            tv3month.setOnClickListener {
                text()
                etInsertVisitDate.setText("3개월")
                tv3month1.setTextColor(Color.parseColor("#4B4B4B"))
                tv3month2.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tv6month.setOnClickListener {
                text()
                etInsertVisitDate.setText("6개월")
                tv6month1.setTextColor(Color.parseColor("#4B4B4B"))
                tv6month2.setTextColor(Color.parseColor("#4B4B4B"))
            }
            tvDateSetting.setOnClickListener {
                text()
                etInsertVisitDate.setText("직접")
                tvDateSetting1.setTextColor(Color.parseColor("#4B4B4B"))
                tvDateSetting2.setTextColor(Color.parseColor("#4B4B4B"))
                etInsertVisitDate.setEnabled(true)
            }
        }
    }

    private fun receive() {

        var yes = false
        var no = false

        binding.apply {

            fun text() {
                if (yes && no) {
                    etInsertReceive.setText("모두")
                } else if (yes && !no) {
                    etInsertReceive.setText("Y")
                } else if (!yes && no) {
                    etInsertReceive.setText("N")
                } else {
                    etInsertReceive.setText("모두")
                }
            }

            tvReceiveY.setOnClickListener {
                yes = !yes
                if (yes) {
                    tvReceiveY.setTextColor(Color.parseColor("#4B4B4B"))
                } else {
                    tvReceiveY.setTextColor(Color.parseColor("#BCBCBC"))
                }
                text()
            }
            tvReceiveN.setOnClickListener {
                no = !no
                if (no) {
                    tvReceiveN.setTextColor(Color.parseColor("#4B4B4B"))
                } else {
                    tvReceiveN.setTextColor(Color.parseColor("#BCBCBC"))
                }
                text()
            }

        }
    }


}