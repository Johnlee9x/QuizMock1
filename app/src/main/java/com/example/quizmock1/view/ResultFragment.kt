package com.example.quizmock1.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizmock1.R
import com.example.quizmock1.databinding.FragmentResultBinding
import com.example.quizmock1.viewmodel.IScoreViewModel
import com.example.quizmock1.viewmodel.ScoreViewModel

class ResultFragment : Fragment() {
    private lateinit var scoreViewModelResultFragment: IScoreViewModel
    private lateinit var resultBinding: FragmentResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scoreViewModelResultFragment = ViewModelProvider(this.requireActivity()).get(ScoreViewModel::class.java)
        resultBinding = FragmentResultBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return resultBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showResult()
        resultBinding.btExist.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_signInFragment)
        }

       resultBinding.btPlayagain.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_homePageFragment)
        }
    }

    private fun showResult(){
        scoreViewModelResultFragment.getScore().observe(this, Observer {score ->
            if(score != null){
                Log.d("scoreResult", score.toString())
                resultBinding.apply {
                    numCrt.text = score.correct
                    Log.d("numCr", score.correct)
                    numWrongans.text = score.wrong
                    Log.d("numWr", score.wrong)
                }
            }
        })
    }

}