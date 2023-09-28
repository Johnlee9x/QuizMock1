package com.example.quizmock1.data.repo
import androidx.lifecycle.MutableLiveData
interface IBaseRepo<T> {
    fun getAll(liveData: MutableLiveData<List<T>>)
    fun add(nameVar: T)
}