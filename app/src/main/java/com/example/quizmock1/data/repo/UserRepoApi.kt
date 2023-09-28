package com.example.quizmock1.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.quizmock1.data.model.firebase.User

class UserRepoApi: IBaseRepo<User> {
    override fun getAll(liveData: MutableLiveData<List<User>>) {

    }
    override fun add(nameVar: User) {
        TODO("Not yet implemented")
    }
}