package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalproject.databinding.FragmentFirebaseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseFragment : Fragment() {

    lateinit var dbRef : DatabaseReference
    lateinit var fAuth: FirebaseAuth
    private var _binding: FragmentFirebaseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirebaseBinding.inflate(inflater, container, false)
        val rootView = binding.root
        dbRef = Firebase.database.reference
        fAuth = Firebase.auth
        val username = binding.editTextTextEmailAddress.text.toString()
        binding.registerButton.setOnClickListener(){
            firebaseSignUp(username, )
        }

        return rootView
    }

    fun firebaseSignUp(){
        fAuth.createUserWithEmailAndPassword()
    }
}