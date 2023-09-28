package com.example.quizmock1.data.repo
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class UserAuthenRepo: IUserAuthenRepo {

    private var authen: FirebaseAuth = FirebaseAuth.getInstance()

    override fun signIn(email: String, password: String, mark: MutableLiveData<Boolean>) {
       authen.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
           if(task.isSuccessful){
               mark.postValue(true)
           }
           else{
               mark.postValue(false)
           }
       }
    }

    override fun signOut(mark: MutableLiveData<Boolean>) {
        authen.signOut()
        mark.postValue(true)
    }

    override fun forgot(emailLiveData: MutableLiveData<Boolean>, email: String) {
        authen.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if(task.isSuccessful){
                emailLiveData.postValue(true)
            }
            else{
                emailLiveData.postValue(false)
            }
        }
    }

    override fun signInWithGg() {

    }

    override fun signUp(email: String, password: String, mark: MutableLiveData<Boolean>) {
        authen.createUserWithEmailAndPassword(email, password).addOnCompleteListener{task ->
            if(task.isSuccessful){
                mark.postValue(true)
            }
            else{
                mark.postValue(false)
            }
        }
    }


}