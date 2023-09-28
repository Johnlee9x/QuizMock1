package com.example.quizmock1.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.quizmock1.R
import com.example.quizmock1.databinding.FragmentSignInBinding
import com.example.quizmock1.viewmodel.IUserViewModel
import com.example.quizmock1.viewmodel.UserViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest


class SignInFragment : Fragment() {
    private lateinit var fragmenBinding: FragmentSignInBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val Req_Code: Int = 123
    private lateinit var navController: NavController
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userViewModel: IUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userViewModel = ViewModelProvider(this.requireActivity()).get(UserViewModel::class.java)
        Log.d("userViewMOdelSignIn", userViewModel.toString())
        fragmenBinding = FragmentSignInBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return fragmenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        changeTextGg()
        fragmenBinding.btSignin.setOnClickListener{
            val email = fragmenBinding.edtEmailInputSignin.text.toString()
            val password = fragmenBinding.edtSigninPasswordInput.text.toString()
            Log.d("email", email)
            Log.d("passw", password)
            try {
                signInWithAccount(email, password)
            }catch (e: Exception){
                Log.d("signINException", e.toString())
            }

        }
        fragmenBinding.txtSignup.setOnClickListener {
           goSignUp()
        }
        fragmenBinding.signInButton.setOnClickListener {
            signInGoogle()
        }
        fragmenBinding.txtfgotpssw.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_forgotFragment)
        }

    }


    private fun goSignUp() {
        navController.navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    private fun signInWithAccount(email: String, password: String){
        if(email.isEmpty() || password.isEmpty()){

            Toast.makeText(context, "Input Missmatch", Toast.LENGTH_SHORT).show()
        }
        else{

            userViewModel.signIn(email, password)
            userViewModel.getMarkUserViewModel().observe(this.viewLifecycleOwner, Observer {task->
                if(task){
                    Toast.makeText(context, "sign in ok", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.action_signInFragment_to_homePageFragment)
                }
                else{
                    Toast.makeText(context, "sign in failed\n try again", Toast.LENGTH_SHORT).show()
                }
            })

        }

    }
    private fun signInGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(
                "129237050204-bi8r95l7q418ndi84rsibjfvh0bgoka4.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this@SignInFragment.requireContext(), gso)
        firebaseAuth = FirebaseAuth.getInstance()
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)

    }

    // onActivityResult() function : this is where
    // we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }
    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                findNavController().navigate(R.id.action_signInFragment_to_homePageFragment)
            }
        } catch (e: ApiException) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    private fun changeTextGg(){
        val txtGg = fragmenBinding.signInButton.getChildAt(0) as TextView
        txtGg.text = "CONTINUE WITH GOOGLE"
        txtGg.textSize = 17F
        txtGg.setTextColor(Color.BLACK)

    }

}