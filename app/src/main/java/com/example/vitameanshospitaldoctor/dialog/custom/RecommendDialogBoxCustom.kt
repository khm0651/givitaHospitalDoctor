package com.example.vitameanshospitaldoctor.dialog.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener
import androidx.lifecycle.*
import com.example.vitameanshospitaldoctor.R
import com.example.vitameanshospitaldoctor.databinding.RecommendItemBinding
import com.example.vitameanshospitaldoctor.databinding.RecommendLayoutBoxBinding
import com.example.vitameanshospitaldoctor.utils.Util.init
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RecommendDialogBoxCustom @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    :ConstraintLayout(context, attrs, defStyleAttr){

    private val binding: RecommendLayoutBoxBinding
//    private lateinit var state : State
    private var firstState = true
    private var secondState = true
    private var thirdState = true
    private var fourState = true

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
        binding.apply {
            personRecommendLayoutText.text = textWhat
            recommendItem1.findViewById<TextView>(R.id.person_recommend_text).text = textFirst
            recommendItem2.findViewById<TextView>(R.id.person_recommend_text).text = textSecond
            recommendItem3.findViewById<TextView>(R.id.person_recommend_text).text = textThird
            recommendItem4.findViewById<TextView>(R.id.person_recommend_text).text = textFour
            var switchItem1 = recommendItem1.findViewById<Switch>(R.id.person_recommend_switch)
            var switchItem2 = recommendItem2.findViewById<Switch>(R.id.person_recommend_switch)
            var switchItem3 = recommendItem3.findViewById<Switch>(R.id.person_recommend_switch)
            var switchItem4 = recommendItem4.findViewById<Switch>(R.id.person_recommend_switch)
//            if(recommendItem1.findViewById<Switch>(R.id.person_recommend_switch).isChecked){
//                recommendItem2.findViewById<Switch>(R.id.person_recommend_switch).isChecked = false
//                recommendItem3.findViewById<Switch>(R.id.person_recommend_switch).isChecked = false
//                recommendItem4.findViewById<Switch>(R.id.person_recommend_switch).isChecked = false
//            } else if(recommendItem2.findViewById<Switch>(R.id.person_recommend_switch).isChecked ||
//                recommendItem3.findViewById<Switch>(R.id.person_recommend_switch).isChecked ||
//                recommendItem4.findViewById<Switch>(R.id.person_recommend_switch).isChecked){
//                recommendItem1.findViewById<Switch>(R.id.person_recommend_switch).isChecked = false
//            }
            switchItem1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                println("테스트")
                firstState = isChecked
                if(firstState){
                    secondState = true
                    thirdState = true
                    fourState = true
                }
                recommendItem1.listener(isChecked)
                switchItem1.isChecked = firstState
                switchItem2.isChecked = secondState
                switchItem3.isChecked = thirdState
                switchItem4.isChecked = fourState
            })
            switchItem2.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                secondState = isChecked
                setState(secondState,isChecked)
                recommendItem2.listener(isChecked)
                switchItem1.isChecked = firstState
                switchItem2.isChecked = secondState
            })
            switchItem3.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                thirdState = isChecked
                setState(thirdState,isChecked)
                recommendItem3.listener(isChecked)
                switchItem1.isChecked = firstState
                switchItem3.isChecked = thirdState
            })
            switchItem4.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                fourState = isChecked
                setState(fourState,isChecked)
                recommendItem4.listener(isChecked)
                switchItem1.isChecked = firstState
                switchItem4.isChecked = fourState
            })
        }
        typedArray.recycle()  //recycle() 메서드를 사용하면 앱에서 최대한 빨리 메모리를 회수할 수 있습니다. // TypedArray 객체는 공유 리소스이며 사용 후 재활용해야 합니다.
    }

    private fun setState(state: Boolean, isChecked: Boolean){
        var state = isChecked
        if(secondState && thirdState && fourState) {
            firstState = true
        } else if (!state) {
            firstState = false
        }
    }

}

