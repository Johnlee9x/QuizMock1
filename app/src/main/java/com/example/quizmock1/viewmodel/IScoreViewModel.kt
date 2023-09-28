package com.example.quizmock1.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.quizmock1.data.model.firebase.Scores

interface IScoreViewModel {
    fun getScore(): MutableLiveData<Scores>
    fun saveScore(numCrt: String, numWrong: String)
    fun getScoreFromRepo(score: Scores)
}