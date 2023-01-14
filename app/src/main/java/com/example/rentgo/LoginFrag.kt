package com.example.rentgo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentLoginBinding
import android.text.method.PasswordTransformationMethod

class LoginFrag : Fragment() {


    lateinit var binding:FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Suppress("DEPRECATION")
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.textView12).setOnClickListener{
            view.findNavController().navigate(R.id.action_loginFrag_to_signupFrag)
        }

        var cpt = 0
        binding.eyelogin.setOnClickListener{

            //val eye = view.findViewById<View>(R.id.eyelogin)
            //val pwd = view.findViewById<EditText>(R.id.passwd)


            if(cpt==0)
            {
                cpt = 1
                binding.eyelogin.setBackgroundResource(R.drawable.eye_off)
                binding.passwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            else
            {
                cpt = 0
                binding.eyelogin.setBackgroundResource(R.drawable.password_eye)
                binding.passwd.transformationMethod = PasswordTransformationMethod.getInstance()
            }


        }

        binding.button2.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }


    }

}