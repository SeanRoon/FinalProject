package com.example.finalproject

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.finalproject.databinding.FragmentLoginBinding
import com.example.finalproject.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            binding.root.findNavController().navigate(R.id.action_registerFragment_to_homeScreenFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val rootView = binding.root
        mAuth = Firebase.auth
        binding.loginNow.setOnClickListener(){
            rootView.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.btnRegister.setOnClickListener(){
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()

            if (binding.emailText.text.toString().equals("")){
                Toast.makeText(activity, "Enter email", Toast.LENGTH_SHORT).show()
            }
            if (binding.passwordText.text.toString().equals("")){
                Toast.makeText(activity, "Enter password", Toast.LENGTH_SHORT).show()
            }
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    binding.progressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        Toast.makeText(activity, "Account created:", Toast.LENGTH_SHORT,).show()
                        rootView.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    } else {
                        Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                    }
                }
        }
        return rootView
    }
}