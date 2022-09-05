package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var optionslocked: Boolean = false
    private var mUserName: String? =null

    private var mCorrectAnswers: Int = 0

    private var progressbar: ProgressBar?=null
    private var tv_progress: TextView?=null
    private var tv_question: TextView?=null
    private var iv_flags: ImageView?=null
    private var btn_submit: Button?=null

    private var optionone: TextView?=null
    private var optiontwo: TextView?=null
    private var optionthree: TextView?=null
    private var optionfour: TextView?=null

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)


        mUserName = intent.getStringExtra(Constants.USER_NAME)


        progressbar = findViewById(R.id.progressbar)
        tv_progress = findViewById(R.id.tv_progressbar)
        tv_question = findViewById(R.id.tv_question)
        iv_flags = findViewById(R.id.iv_flags)
        btn_submit = findViewById(R.id.submit)

        optionone = findViewById(R.id.optionone)
        optiontwo = findViewById(R.id.optiontwo)
        optionthree = findViewById(R.id.optionthree)
        optionfour = findViewById(R.id.optionfour)
        btn_submit = findViewById(R.id.submit)

        optionone?.setOnClickListener(this)
        optiontwo?.setOnClickListener(this)
        optionthree?.setOnClickListener(this)
        optionfour?.setOnClickListener(this )
        btn_submit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestions()
        Log.i("LOGi QuestionsList Size is ", "${mQuestionsList!!.size}")     //

        progressbar?.max = mQuestionsList!!.size

        setQuestion()
    }

    private fun setQuestion() {
        defaultOptionView()
        optionslocked=false
        val question: Question = mQuestionsList!![mCurrentPosition - 1]    //

        progressbar?.progress = mCurrentPosition
        tv_progress?.text = "$mCurrentPosition/${progressbar?.max}"
        tv_question?.text = question.question

        optionone?.text = question.optionOne
        optiontwo?.text = question.optionTwo
        optionthree?.text = question.optionThree
        optionfour?.text = question.optionFour

        iv_flags?.setImageResource(question.image)  //

        if (mCurrentPosition==mQuestionsList!!.size){
            btn_submit?.text = "FINISH"
        }
        else{
            btn_submit?.text = "SUBMIT"
        }
    }

    private fun defaultOptionView(){
        val options = ArrayList<TextView>()

        optionone?.let{
            options.add(it)
        }
        optiontwo?.let{
            options.add(it)
        }
        optionthree?.let{
            options.add(it)
        }
        optionfour?.let{
            options.add(it)
        }
        Log.i("LOGi Arraylist Check: ","${options[0].text}")
        Log.i("LOGi Arraylist Check: ","${options[1].text}")
        Log.i("LOGi Arraylist Check: ","${options[2].text}")
        Log.i("LOGi Arraylist Check: ","${options[3].text}")

        for (option in options)
        {
            option.setTextColor(ContextCompat.getColor(this, R.color.black))
            option.typeface = Typeface.DEFAULT

            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
         }
    }

    private fun selectedOptionView(tv: TextView, selectedOption: Int)
    {
        defaultOptionView()
        mSelectedOptionPosition = selectedOption

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

    }

    private fun selectedanswerView(answer: Int, drawableView: Int)
    {   //important
        //here we are passing a drawable as integer

        when(answer){
            1->{
                optionone?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2->{
                optiontwo?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3->{
                optionthree?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4->{
                optionfour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){

            R.id.optionone -> {
                if (!optionslocked)
                optionone?.let{if (optionslocked==false)
                    selectedOptionView(it, 1)
                }
            }
            R.id.optiontwo -> {
                if (!optionslocked)
                optiontwo?.let{
                    selectedOptionView(it, 2)
                }
            }
            R.id.optionthree -> {
                if (!optionslocked)
                optionthree?.let{
                    selectedOptionView(it, 3)
                }
            }
            R.id.optionfour -> {
                if (!optionslocked)
                optionfour?.let{
                    selectedOptionView(it,4)
                }
            }

            //
            R.id.submit -> {
                if (mSelectedOptionPosition==0)
                {
                    mCurrentPosition++
                    when{
                        mCurrentPosition<=mQuestionsList!!.size ->{
                            setQuestion()
                        }
                        else->{
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else
                {

                    val question = mQuestionsList?.get(mCurrentPosition-1)
                    if (mSelectedOptionPosition!=question!!.correctAnswer)
                    {
                        selectedanswerView(mSelectedOptionPosition, R.drawable.incorrect_option_border_bg)
                    }
                    else
                    {
                        mCorrectAnswers++
                    }

                    selectedanswerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                    optionslocked=true

                    if(mCurrentPosition==mQuestionsList?.size)
                    {
                        btn_submit?.text = "FINISH"
                    }
                    else
                    {
                        btn_submit?.text = "NEXT"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }
}