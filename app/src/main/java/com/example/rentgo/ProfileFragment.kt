package com.example.rentgo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.rentgo.databinding.FragmentProfileBinding



class ProfileFragment : Fragment() {


    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logoutButton = view.findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            val intent = Intent(context,LoginSignUpActivity::class.java)
            this.startActivity(intent)
            val pref = requireActivity().getSharedPreferences("users", Context.MODE_PRIVATE)
            pref.edit { putBoolean("connected", false)}
            requireActivity().finish()
        }
    }

}