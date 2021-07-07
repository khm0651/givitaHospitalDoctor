package com.example.vitameanshospitaldoctor.dialog.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.databinding.RecommendItemBinding
import com.example.vitameanshospitaldoctor.databinding.RecommendLayoutBoxBinding

class RecommendDialogBoxCustom @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    :ConstraintLayout(context, attrs, defStyleAttr){

    private val binding: RecommendLayoutBoxBinding

    init{
        binding = RecommendLayoutBoxBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
        getAttrs(attrs)
        getAttrs(attrs,defStyleAttr)
    }

    private fun getAttrs(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.recommend_layout_box)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyle: Int){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.recommend_layout_box,defStyle,0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray){
        var textWhat = typedArray.getText(R.styleable.recommend_layout_box_text_what)
        var textFirst = typedArray.getText(R.styleable.recommend_layout_box_text_first)
        var textSecond = typedArray.getText(R.styleable.recommend_layout_box_text_second)
        var textThird = typedArray.getText(R.styleable.recommend_layout_box_text_third)
        var textFour = typedArray.getText(R.styleable.recommend_layout_box_text_four)
        binding.personRecommendLayoutText.text = textWhat
        binding.recommendItem1.findViewById<TextView>(R.id.person_recommend_text).text = textFirst
        binding.recommendItem2.findViewById<TextView>(R.id.person_recommend_text).text = textSecond
        binding.recommendItem3.findViewById<TextView>(R.id.person_recommend_text).text = textThird
        binding.recommendItem4.findViewById<TextView>(R.id.person_recommend_text).text = textFour
        typedArray.recycle()  //recycle() 메서드를 사용하면 앱에서 최대한 빨리 메모리를 회수할 수 있습니다. // TypedArray 객체는 공유 리소스이며 사용 후 재활용해야 합니다.
    }
}