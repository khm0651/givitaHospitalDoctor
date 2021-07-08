package com.example.vitameanshospitaldoctor.dialog.custom

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.databinding.RecommendItemBinding
import kotlin.properties.Delegates

class RecommendDialogItemCustom @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: RecommendItemBinding
    private var flag: Int = 1
    private var state by Delegates.notNull<Boolean>()

    init {
        binding = RecommendItemBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
        setListener()
        getAttrs(attrs)
        getAttrs(attrs,defStyleAttr)
    }

    private fun getAttrs(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.recommend_layout)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyle: Int){
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.recommend_layout,defStyle,0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray){
        val text = typedArray.getText(R.styleable.recommend_layout_text)
        state = typedArray.getBoolean(R.styleable.recommend_layout_state,false)
        binding.personRecommendText.text = text
        if(state) binding.personRecommendSwitch.isChecked = true
        typedArray.recycle()  //recycle() 메서드를 사용하면 앱에서 최대한 빨리 메모리를 회수할 수 있습니다.
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun setListener() {
        binding.apply {
            personRecommendSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

                recommendTextWeak.setOnClickListener {
                    if(isChecked) flag = 0
                    flagNumber(flag,isChecked)
                }
                recommendTextMedium.setOnClickListener {
                    if(isChecked) flag = 1
                    flagNumber(flag,isChecked)
                }
                recommendTextStrong.setOnClickListener {
                    if(isChecked) flag = 2
                    flagNumber(flag,isChecked)
                }
                if(isChecked){
                    personRecommendText.setTextColor(resources.getColor(R.color.recommend_text_title_select,null))
                    flagNumber(flag,isChecked)
                } else {
                    personRecommendText.setTextColor(resources.getColor(R.color.recommend_text_title,null))
                    recommendTextWeak.setTextColor(resources.getColor(R.color.recommend_text))
                    recommendTextMedium.setTextColor(resources.getColor(R.color.recommend_text))
                    recommendTextStrong.setTextColor(resources.getColor(R.color.recommend_text))
                }
            })
        }
    }

    private fun flagNumber(num: Int, boolean: Boolean) {
        binding.apply {
            if(boolean){
                when(num){
                    0 -> {
                        recommendTextWeak.setTextColor(resources.getColor(R.color.recommend_text_select))
                        recommendTextMedium.setTextColor(resources.getColor(R.color.recommend_text))
                        recommendTextStrong.setTextColor(resources.getColor(R.color.recommend_text))
                    }
                    1 -> {
                        recommendTextWeak.setTextColor(resources.getColor(R.color.recommend_text))
                        recommendTextMedium.setTextColor(resources.getColor(R.color.recommend_text_select))
                        recommendTextStrong.setTextColor(resources.getColor(R.color.recommend_text))
                    }
                    else -> {
                        recommendTextWeak.setTextColor(resources.getColor(R.color.recommend_text))
                        recommendTextMedium.setTextColor(resources.getColor(R.color.recommend_text))
                        recommendTextStrong.setTextColor(resources.getColor(R.color.recommend_text_select))
                    }
                }
            }
        }
    }
}
