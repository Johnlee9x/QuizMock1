package com.example.quizmock1.data.repo

import androidx.lifecycle.MutableLiveData

interface IUserAuthenRepo {
    fun signIn(email: String, password: String, mark: MutableLiveData<Boolean>)
    fun signOut(mark : MutableLiveData<Boolean>)
    fun forgot(emailLiveData: MutableLiveData<Boolean>, email : String)
    fun signInWithGg()
    fun signUp(email: String, password: String, mark: MutableLiveData<Boolean>)
}