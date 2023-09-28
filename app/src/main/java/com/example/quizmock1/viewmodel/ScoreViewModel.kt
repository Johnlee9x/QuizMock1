package com.example.quizmock1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizmock1.data.model.firebase.Scores
import com.example.quizmock1.data.repo.IScoreRepo
import com.example.quizmock1.data.repo.ScoreRepo
import com.google.android.material.color.utilities.Score
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

class ScoreViewModel: ViewModel(), IScoreViewModel {
    private val scoreMutable = MutableLiveData<Scores>()
    private var scoreRepo: IScoreRepo = ScoreRepo(this)

    init {
        scoreRepo.getScore()
    }

    override fun getScore(): MutableLiveData<Scores> {
        return scoreMutable
    }

    override fun getScoreFromRepo(score: Scores){
        this.scoreMutable.value = score
    }

    override fun saveScore(numCrt: String, numWrong: String){
        scoreRepo.saveScore(numCrt, numWrong)
    }

}