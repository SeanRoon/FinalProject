package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.finalproject.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val rootView = binding.root
        mAuth = Firebase.auth
        binding.registerNow.setOnClickListener(){
            rootView.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnLogin.setOnClickListener(){
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()

            if (binding.emailText.text.toString().equals("")){
                Toast.makeText(activity, "Enter email", Toast.LENGTH_SHORT).show()
            }
            if (binding.passwordText.text.toString().equals("")){
                Toast.makeText(activity, "Enter password", Toast.LENGTH_SHORT).show()
            }
        }
        return rootView
    }
}