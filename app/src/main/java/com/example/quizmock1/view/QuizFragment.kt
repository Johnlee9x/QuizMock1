package com.example.quizmock1.view

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.quizmock1.R
import com.example.quizmock1.databinding.FragmentQuizBinding
import com.example.quizmock1.viewmodel.IScoreViewModel
import com.example.quizmock1.viewmodel.QuizViewModel
import com.example.quizmock1.viewmodel.IQuizViewModel
import com.example.quizmock1.viewmodel.ScoreViewModel


class QuizFragment : Fragment() {
    private lateinit var scoreViewModel: IScoreViewModel
    private lateinit var quizViewModel: IQuizViewModel
    private lateinit var fragmentQuizBinding: FragmentQuizBinding
    private lateinit var quizViewModel1: IQuizViewModel
    private lateinit var countDown: CountDownTimer
    private var crectAns = ""
    private var numCrt = 0
    private var numWrong = 0
    var qtionNumCrt = 0
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scoreViewModel = ViewModelProvider(this.requireActivity())[ScoreViewModel::class.java]
        quizViewModel = ViewModelProvider(this.requireActivity())[QuizViewModel::class.java]
        fragmentQuizBinding = FragmentQuizBinding.inflate(inflater, container, false)

        return fragmentQuizBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        showQtion()
        fragmentQuizBinding.btnNext.setOnClickListener {
            showQtion()
        }
        fragmentQuizBinding.btnFinish.setOnClickListener {
            saveScore()
        }
        fragmentQuizBinding.txtA.setOnClickListener {
            stopCntDown()
            if(crectAns == "ansA"){
                numCrt++
                fragmentQuizBinding.txtA.setBackgroundColor(Color.GREEN)
                fragmentQuizBinding.txtNumcrt.text = "$numCrt"
            }
            else{
                numWrong++
                fragmentQuizBinding.txtA.setBackgroundColor(Color.RED)
                fragmentQuizBinding.txtNumwrong.text = "$numWrong"
            }
            closeClickAble()
        }
        fragmentQuizBinding.txtB.setOnClickListener {
            stopCntDown()
            if(crectAns =="ansB"){
                numCrt++
                fragmentQuizBinding.txtB.setBackgroundColor(Color.GREEN)
                fragmentQuizBinding.txtNumcrt.text = "$numCrt"

            }else{
                numWrong++
                fragmentQuizBinding.txtB.setBackgroundColor(Color.RED)
                fragmentQuizBinding.txtNumwrong.text = "$numWrong"
            }
            closeClickAble()


        }
        fragmentQuizBinding.txtC.setOnClickListener {
            stopCntDown()
            if(crectAns == "ansC"){
                numCrt++
                fragmentQuizBinding.txtC.setBackgroundColor(Color.GREEN)
                fragmentQuizBinding.txtNumcrt.text = "$numCrt"
            }
            else{
                numWrong++
                fragmentQuizBinding.txtC.setBackgroundColor(Color.RED)
                fragmentQuizBinding.txtNumwrong.text = "$numWrong"
            }
            closeClickAble()

        }
        fragmentQuizBinding.txtD.setOnClickListener {
            stopCntDown()
            if(crectAns == "ansD"){
                numCrt++
                fragmentQuizBinding.txtD.setBackgroundColor(Color.GREEN)
                fragmentQuizBinding.txtNumcrt.text = "$numCrt"
            }
            else{
                numWrong ++
                fragmentQuizBinding.txtD.setBackgroundColor(Color.RED)
                fragmentQuizBinding.txtNumwrong.text = "$numWrong"
            }
            closeClickAble()
        }

    }

    fun closeClickAble(){
        fragmentQuizBinding.txtA.isClickable = false
        fragmentQuizBinding.txtB.isClickable = false
        fragmentQuizBinding.txtC.isClickable = false
        fragmentQuizBinding.txtD.isClickable = false
    }
    fun refreshOptions(){
        starCntDown()
        fragmentQuizBinding.txtA.isClickable = true
        fragmentQuizBinding.txtB.isClickable = true
        fragmentQuizBinding.txtC.isClickable = true
        fragmentQuizBinding.txtD.isClickable = true

        fragmentQuizBinding.txtA.setBackgroundColor(Color.WHITE)
        fragmentQuizBinding.txtB.setBackgroundColor(Color.WHITE)
        fragmentQuizBinding.txtC.setBackgroundColor(Color.WHITE)
        fragmentQuizBinding.txtD.setBackgroundColor(Color.WHITE)
    }

    fun showQtion(){
        refreshOptions()
        quizViewModel.getQuestionsLiveData().observe(this, Observer {list ->
            val size = list.size
            Log.d("size", size.toString())
            if(qtionNumCrt <= size - 1){
                fragmentQuizBinding.apply {
                    txtQuestion.text = list[qtionNumCrt].question
                    txtA.text = list[qtionNumCrt].ansA
                    txtB.text = list[qtionNumCrt].ansB
                    txtC.text = list[qtionNumCrt].ansC
                    txtD.text = list[qtionNumCrt].ansD
                    crectAns = list[qtionNumCrt].crAnswer
                }
            }
            else{
                stopCntDown()
                val dialogMsg = AlertDialog.Builder(this@QuizFragment.requireActivity())
                dialogMsg.setTitle("Quiz Game")
                dialogMsg.setMessage("Finish the Quiz, see the result")
                dialogMsg.setCancelable(false)
                dialogMsg.setPositiveButton("Result"){ _, _
                    -> saveScore()

                }
                dialogMsg.setNegativeButton("play again"){_,_
                    -> findNavController().navigate(R.id.action_quizFragment_to_homePageFragment)

                }
                dialogMsg.create().show()
            }
            qtionNumCrt++
            fragmentQuizBinding.progressBar.visibility = View.INVISIBLE
            fragmentQuizBinding.layoutProgressBar.visibility =View.INVISIBLE
            fragmentQuizBinding.layoutTime.visibility = View.VISIBLE
            fragmentQuizBinding.layoutQuestion.visibility = View.VISIBLE
            fragmentQuizBinding.layoutBtn.visibility = View.VISIBLE

        })
    }

    fun starCntDown(){
        countDown = object : CountDownTimer(30000, 1000) {
            override fun onTick(tilFn: Long) {
                fragmentQuizBinding.txtCntdown.text = (tilFn / 1000).toString()
            }

            override fun onFinish() {
                closeClickAble()
                numWrong++
                fragmentQuizBinding.txtQuestion.text = getString(R.string.time_over)
                fragmentQuizBinding.txtNumwrong.text = numWrong.toString()
                simulateAutoButtonClick()
            }

        }.start()
    }

    private fun simulateAutoButtonClick() {
        val handler = Handler()
        handler.postDelayed({
            fragmentQuizBinding.btnNext.performClick()
        }, 1)
    }
    fun stopCntDown(){
        countDown.cancel()
    }

    private fun saveScore(){
        scoreViewModel.saveScore(numCrt.toString(), numWrong.toString())
        navController.navigate(R.id.action_quizFragment_to_resultFragment)
    }

}