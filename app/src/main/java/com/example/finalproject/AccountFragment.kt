package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.finalproject.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    lateinit var dbRef: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val rootView = binding.root
        dbRef = Firebase.database.reference
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        if (user == null)
            rootView.findNavController().navigate(R.id.action_homeScreenFragment_to_loginFragment)
        else{

        }
        binding.saveButton.setOnClickListener(){
            if(binding.genderSwitch.isChecked)
                    dbRef.child(user.uid).child("gender").push().setValue("Female")
            else
                    dbRef.child(user.uid).child("gender").push().setValue("Male")
            dbRef.child(user.uid).child("heightInFeet").push().setValue(binding.userHeightInFeet.text.toString().toInt())
            dbRef.child(user.uid).child("heightInInches").push().setValue(binding.userHeightInInches.text.toString().toInt())
            dbRef.child(user.uid).child("currentWeight").push().setValue(binding.userWeight.text.toString().toInt())
            dbRef.child(user.uid).child("targetWeight").push().setValue(binding.userTargetWeight.text.toString().toInt())
        }
        binding.logoutButton.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()
            rootView.findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }
        return rootView
    }
}