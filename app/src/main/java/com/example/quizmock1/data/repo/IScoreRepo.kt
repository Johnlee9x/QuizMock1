package com.example.quizmock1.data.repo

import androidx.lifecycle.MutableLiveData
import com.google.android.material.color.utilities.Score

interface IScoreRepo {
    fun saveScore(numCrt: String, numWrong: String)
    fun getScore()
    fun getListScore()
}