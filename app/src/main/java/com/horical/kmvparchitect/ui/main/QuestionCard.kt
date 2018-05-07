package com.horical.kmvparchitect.ui.main

import android.content.Context
import android.graphics.Color
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.horical.kmvparchitect.R
import com.kev.karchitect.utils.images.ImageLoader
import com.mindorks.placeholderview.annotations.*

@NonReusable
@Layout(R.layout.card_layout)
class QuestionCard constructor(private val context: Context, private val question: QuestionCardData) {

    @View(R.id.btn_option_1)
    lateinit var option1Button: Button

    @View(R.id.btn_option_2)
    lateinit var option2Button: Button

    @View(R.id.btn_option_3)
    lateinit var option3Button: Button

    @View(R.id.iv_pic)
    lateinit var picImageView: ImageView

    @View(R.id.tv_question_txt)
    lateinit var questionTextView: TextView

    @Click(R.id.btn_option_1)
    internal fun onOption1Click() {
        showCorrectOptions()
    }

    @Click(R.id.btn_option_2)
    internal fun onOption2Click() {
        showCorrectOptions()
    }

    @Click(R.id.btn_option_3)
    internal fun onOption3Click() {
        showCorrectOptions()
    }

    @Resolve
    private fun onResolved() {
        questionTextView.text = question.quest.questionText
        for (i in 0..2) {
            var button: Button? = null
            when (i) {
                0 -> button = option1Button
                1 -> button = option2Button
                2 -> button = option3Button
            }
            button?.text = question.options[i].optionText
            ImageLoader.load(context, question.quest.questionImgUrl, picImageView)
        }
    }

    private fun showCorrectOptions() {
        for (i in 0..2) {
            val option = question.options[i]
            var button: Button? = null
            when (i) {
                0 -> button = option1Button
                1 -> button = option2Button
                2 -> button = option3Button
            }
            button?.let {
                if (option.isCorrect) it.setBackgroundColor(Color.GREEN) else it.setBackgroundColor(Color.RED)
            }
        }
    }

}