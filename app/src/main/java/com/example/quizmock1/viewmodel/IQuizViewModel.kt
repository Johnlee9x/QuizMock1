package com.example.quizmock1.viewmodel

import androidx.lifecycle.LiveData
import com.example.quizmock1.data.model.firebase.Questions

interface IQuizViewModel {
    fun getQuestionsLiveData(): LiveData<List<Questions>>
}