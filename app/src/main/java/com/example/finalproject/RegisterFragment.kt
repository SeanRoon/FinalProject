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
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.finalproject.databinding.FragmentLoginBinding
import com.example.finalproject.databinding.FragmentRegisterBinding
import com.example.finalproject.model.FoodViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth
    private lateinit var user: FirebaseUser
    lateinit var dbRef: DatabaseReference
    private val viewModel: FoodViewModel by activityViewModels()


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
        dbRef = Firebase.database.reference
        mAuth = Firebase.auth
        binding.loginNow.setOnClickListener(){
            rootView.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.btnRegister.setOnClickListener(){
            binding.progressBar.visibility = View.VISIBLE
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()

            if (binding.emailText.text.toString().equals("")){
                Toast.makeText(activity, "Enter email", Toast.LENGTH_LONG).show()
            }
            else if (binding.passwordText.text.toString().equals("")){
                Toast.makeText(activity, "Enter password", Toast.LENGTH_LONG).show()
            }
            else {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        binding.progressBar.visibility = View.GONE
                        if (task.isSuccessful) {
                            Toast.makeText(
                                activity,
                                "Account created; Signing in",
                                Toast.LENGTH_LONG,
                            ).show()
                            mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener() { task ->
                                    if (task.isSuccessful) {
                                        rootView.findNavController()
                                            .navigate(R.id.action_registerFragment_to_accountFragment)
                                    } else {
                                        Toast.makeText(
                                            activity,
                                            "Authentication failed.",
                                            Toast.LENGTH_LONG,
                                        )
                                            .show()
                                    }
                                }
//                        user = mAuth.currentUser!!
//                        dbRef.child(user.uid).child("caloriesEaten").push().setValue(0)
                        } else {
                            Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_LONG,)
                                .show()
                        }
                    }
            }
        }
        return rootView
    }
}