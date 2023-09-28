package com.example.quizmock1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizmock1.data.model.firebase.Questions
import com.example.quizmock1.data.repo.QuestionRepo
import com.example.quizmock1.data.repo.IBaseRepo

class QuizViewModel: ViewModel(), IQuizViewModel {

    private val listQtLivedata = MutableLiveData<List<Questions>>()
    private val iQuestionRepo: IBaseRepo<Questions> = QuestionRepo()
    init {
        iQuestionRepo.getAll(listQtLivedata)
    }
    override fun getQuestionsLiveData(): LiveData<List<Questions>> = listQtLivedata

}