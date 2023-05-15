package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.finalproject.databinding.FragmentAccountBinding
import com.example.finalproject.databinding.FragmentHomeScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val rootView = binding.root
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        if (user == null)
            rootView.findNavController().navigate(R.id.action_homeScreenFragment_to_loginFragment)
        else{

        }
        val userWeight = binding.userWeight.text
        val userTargetWeight = binding.userTargetWeight.text
        val userHeightInFeet = binding.userHeightInFeet.text
        val userHeightInInches = (userHeightInFeet * 12) + binding.userHeightInInches.text
        binding.logoutButton.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()
            rootView.findNavController().navigate(R.id.action_homeScreenFragment_to_loginFragment)
        }
        return rootView
    }
}