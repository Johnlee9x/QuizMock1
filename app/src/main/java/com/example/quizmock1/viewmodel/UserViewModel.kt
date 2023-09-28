package com.example.quizmock1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizmock1.data.repo.IUserAuthenRepo
import com.example.quizmock1.data.repo.UserAuthenRepo

class UserViewModel: ViewModel(), IUserViewModel {
    private var userAuthen: IUserAuthenRepo = UserAuthenRepo()

    private val userMutableLiveData = MutableLiveData<Boolean>()


    override fun signIn(email: String, password: String) {
        userAuthen.signIn(email, password, userMutableLiveData)
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }


    override fun signUp(email: String, password: String) {
        userAuthen.signUp(email, password, userMutableLiveData)
    }

    override fun forget(email: String) {
        userAuthen.forgot(userMutableLiveData, email)
    }

    override fun getMarkUserViewModel(): MutableLiveData<Boolean> = userMutableLiveData

}