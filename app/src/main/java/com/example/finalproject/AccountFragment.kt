package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.finalproject.databinding.FragmentAccountBinding
import com.example.finalproject.model.FoodViewModel
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
    private val viewModel: FoodViewModel by activityViewModels()


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
            val weight = binding.userWeight.text.toString().toInt()
            val goalWeight = binding.userTargetWeight.text.toString().toInt()
            val heightInInches = (binding.userHeightInFeet.text.toString().toInt() * 12) + binding.userHeightInInches.text.toString().toInt()
            if(binding.genderSwitch.isChecked)
                dbRef.child(user.uid).child("gender").push().setValue("female")
            else
                dbRef.child(user.uid).child("gender").push().setValue("male")
            dbRef.child(user.uid).child("heightInInches").push().setValue(heightInInches)
            dbRef.child(user.uid).child("currentWeight").push().setValue(weight)
            dbRef.child(user.uid).child("targetWeight").push().setValue(goalWeight)
            viewModel.setTotalCalories(goalWeight * 15)
            rootView.findNavController().navigate(R.id.action_accountFragment_to_homeScreenFragment)
        }
        binding.logoutButton.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()
            rootView.findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
        }
        return rootView
    }
}