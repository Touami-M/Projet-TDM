package com.example.rentgo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.rentgo.databinding.FragmentLoginBinding
import kotlinx.coroutines.*
import okhttp3.MediaType.*
import okhttp3.RequestBody.*


class LoginFrag : Fragment() {


    lateinit var binding: FragmentLoginBinding

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

        view.findViewById<View>(R.id.textView12).setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFrag_to_signupFrag)
        }

        var cpt = 0
        binding.eyelogin.setOnClickListener {

            if (cpt == 0) {
                cpt = 1
                binding.eyelogin.setBackgroundResource(R.drawable.eye_off)
                binding.passwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                cpt = 0
                binding.eyelogin.setBackgroundResource(R.drawable.password_eye)
                binding.passwd.transformationMethod = PasswordTransformationMethod.getInstance()
            }


        }

        val pref = requireActivity().getSharedPreferences("users",Context.MODE_PRIVATE)
        val con = pref.getBoolean("connected", false)

        if(!con) binding.button2.setOnClickListener {
            val telephone = binding.editTextPhone.text.toString().toInt()
            val password = binding.passwd.text.toString()
            val progresbar = binding.progressBar
            progresbar.visibility = View.VISIBLE
            val data: HashMap<String, String> = HashMap()
            data.put("telephone", telephone.toString())
            data.put("password", password)
            login(data)
        }
        else{
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }


    }

    private fun login(data: HashMap<String, String>) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitService.endpoint.getUserMap(data)
            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.INVISIBLE
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        val pref = requireActivity().getSharedPreferences("users", Context.MODE_PRIVATE)
                        pref.edit {
                            putInt("idUser", user.telephone)
                            putBoolean("connected", true)
                        }
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "Wrong email or password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "An error has occurred",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}

