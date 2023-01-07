package com.example.rentgo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentLoginBinding
import com.example.rentgo.databinding.FragmentSignupBinding


class SignupFrag : Fragment() {


    lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.textView12).setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_signupFrag_to_loginFrag)
        }



    }

}