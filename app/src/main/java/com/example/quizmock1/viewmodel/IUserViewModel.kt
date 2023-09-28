package com.example.quizmock1.viewmodel

import androidx.lifecycle.MutableLiveData

interface IUserViewModel {
    fun signIn(email: String, password: String)
    fun signOut()
    fun signUp(email: String, password: String)
    fun forget(email: String)
    fun getMarkUserViewModel(): MutableLiveData<Boolean>
}