package com.example.quizmock1.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizmock1.databinding.FragmentSignUpBinding
import com.example.quizmock1.viewmodel.IUserViewModel
import com.example.quizmock1.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {
    private lateinit var signUpBinding: FragmentSignUpBinding
    private lateinit var userViewModel: IUserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        return signUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        signUpBinding.buttonsignup.setOnClickListener {
            val email: String = signUpBinding.txtInputemailsignup.text.toString()
            val pass: String = signUpBinding.txtInputpasswordsignup.text.toString()
            Log.d("email", email)
            Log.d("pass", pass)
            userViewModel.signUp(email, pass)
            userViewModel.getMarkUserViewModel().observe(this, Observer {mark ->
                if(mark){
                    Toast.makeText(context, "Sign Up ok", Toast.LENGTH_SHORT).show()

                }
            })
        }
    }

}