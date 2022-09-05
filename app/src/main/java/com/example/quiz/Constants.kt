package com.example.quiz

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()

        val ques1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Australia",
            "India",
            "Germany",
            "New Zealand",
            2
        )
        questionList.add(ques1)
        val ques2 = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "India",
            "Australia",
            "Canada",
            "Spain",
            2
        )
        questionList.add(ques2)
        val ques3 = Question(
            3,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "America",
            "Nepal",
            "New Zealand",
            1
        )
        questionList.add(ques3)
        val ques4 = Question(
            4,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Nepal",
            "Belgium",
            "Canada",
            "America",
            2
        )
        questionList.add(ques4)

        val ques5 = Question(
            5,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Nepal",
            "India",
            "America",
            "Kuwait",
            4
        )
        questionList.add(ques5)
        return questionList
    }
}