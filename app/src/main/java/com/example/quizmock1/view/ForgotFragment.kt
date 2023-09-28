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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.quizmock1.R
import com.example.quizmock1.databinding.FragmentForgotBinding
import com.example.quizmock1.viewmodel.IUserViewModel
import com.example.quizmock1.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth

class ForgotFragment : Fragment() {
    lateinit var forgotBinding: FragmentForgotBinding
    private lateinit var authen : FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var userViewModelForgot: IUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authen = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        forgotBinding = FragmentForgotBinding.inflate(inflater, container, false)
        return forgotBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        userViewModelForgot = ViewModelProvider(this.requireActivity()).get(UserViewModel::class.java)
        Log.d("userViewModelForgot", userViewModelForgot.toString())
        forgotBinding.btFgotPassword.setOnClickListener {
            val email = forgotBinding.edtInputFgotpass.text.toString()
            getPassword(email)
        }
    }

    private fun getPassword(email: String) {
        if(email.isBlank() || !email.endsWith("@gmail.com", false)){
            Toast.makeText(context,
                "Email has to be ending with @gmail.com", Toast.LENGTH_SHORT).show()
        }
        else{
            userViewModelForgot.forget(email)
            userViewModelForgot.getMarkUserViewModel().observe(this, Observer {task ->
                if(task){
                    Toast.makeText(context, "password was send to your email", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.action_forgotFragment_to_signInFragment)
                }
                else{
                    Toast.makeText(context, "Reset email failed\n try again", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


}