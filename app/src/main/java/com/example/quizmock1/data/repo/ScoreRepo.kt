package com.example.quizmock1.data.repo

import android.util.Log
import com.example.quizmock1.data.model.firebase.Scores
import com.example.quizmock1.viewmodel.ScoreViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ScoreRepo(private var scoreViewModel: ScoreViewModel): IScoreRepo {
    private var userCr = FirebaseAuth.getInstance().currentUser

    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Scores")
    override fun saveScore(numCrt: String, numWrong: String) {
        database.child(userCr!!.uid).child("correct").setValue(numCrt).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d("checkSavedata", "Success")
            }
            else{
                Log.d("checkSavedata", "failed")
            }
        }
        database.child(userCr!!.uid).child("wrong").setValue(numWrong)
    }

    override fun getScore() {
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("snapShotScoreRepo", snapshot.toString())
                if(snapshot.exists()){
                    try{
                        val score = snapshot.child(userCr!!.uid).getValue(Scores::class.java)
                        Log.d("ScoresCrt", score!!.correct)
                        Log.d("ScoresWr", score.wrong)
                        scoreViewModel.getScoreFromRepo(score)
                    }catch (e: Exception){
                        Log.d("checkExceptionScoreRepo", e.message.toString())
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun getListScore() {
        TODO("Not yet implemented")
    }


}