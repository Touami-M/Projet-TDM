package com.example.rentgo

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentLoginBinding
import android.text.method.PasswordTransformationMethod

class LoginFrag : Fragment() {


    lateinit var binding:FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.textView12).setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_loginFrag_to_signupFrag)
        }


        view.findViewById<View>(R.id.eyelogin).setOnClickListener{

            val eye = view.findViewById<View>(R.id.eyelogin)
            val pwd = view.findViewById<EditText>(R.id.passwd)

            if(eye.equals(R.drawable.password_eye))
            {
                eye.setBackgroundResource(R.drawable.eye_off)
                pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            else
            {
                eye.setBackgroundResource(R.drawable.password_eye)
                pwd.transformationMethod = PasswordTransformationMethod.getInstance()
            }


        }


    }

}