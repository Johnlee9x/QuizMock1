package com.example.quizmock1.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizmock1.R
import com.example.quizmock1.databinding.FragmentHomePageBinding
import com.example.quizmock1.viewmodel.QuizViewModel
import com.example.quizmock1.viewmodel.IQuizViewModel


class HomePageFragment : Fragment() {
    private lateinit var quizwViewModel: IQuizViewModel
    private lateinit var homebinding: FragmentHomePageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homebinding = FragmentHomePageBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return homebinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizwViewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        Log.d("quizwViewModelHP", quizwViewModel.toString())
        homebinding.btStartQuizHomepage.setOnClickListener {
            goQuiz()
        }

    }

    fun goQuiz(){
        findNavController().navigate(R.id.action_homePageFragment_to_quizFragment)
    }
    fun goOut(){
        findNavController().navigate(R.id.action_homePageFragment_to_signInFragment)
    }


}