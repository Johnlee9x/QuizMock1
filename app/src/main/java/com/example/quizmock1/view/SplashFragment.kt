package com.example.quizmock1.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quizmock1.R
import com.example.quizmock1.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private lateinit var splashFragmentBinding: FragmentSplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splashFragmentBinding = FragmentSplashBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return splashFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
            }
        }, 3000)
    }

}