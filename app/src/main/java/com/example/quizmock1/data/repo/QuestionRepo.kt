package com.example.quizmock1.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.quizmock1.data.model.firebase.Questions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class QuestionRepo: IBaseRepo<Questions> {
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var refDaba: DatabaseReference = database.reference.child("Questions")

    override fun getAll(liveData: MutableLiveData<List<Questions>>) {
        refDaba.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("data", snapshot.value.toString())
                if(snapshot.exists()){
                    try {
                        val listQuestion: List<Questions> =
                            snapshot.children.map {
                                    dataSnapshot ->
                                dataSnapshot.getValue(Questions::class.java)!!
                                    .copy(
                                        question = dataSnapshot.child("question").value.toString(),
                                        ansA = dataSnapshot.child("ansA").value.toString(),
                                        ansB = dataSnapshot.child("ansB").value.toString(),
                                        ansC = dataSnapshot.child("ansC").value.toString(),
                                        ansD = dataSnapshot.child("ansD").value.toString(),
                                        crAnswer = dataSnapshot.child("crAnswer").value.toString(),
                                    )
                            }
                        liveData.postValue(listQuestion)
                    } catch (e: Exception) {
                        Log.e("----------------", e.message.toString())
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun add(nameVar: Questions) {
        TODO("Not yet implemented")
    }
}