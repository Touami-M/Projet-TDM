package com.example.rentgo

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.navigation.findNavController
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



        view.findViewById<ImageView>(R.id.eyesignup).setOnClickListener{

            val eye = view.findViewById<View>(R.id.eyesignup)
            val pwd = view.findViewById<EditText>(R.id.passwdsignup)

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