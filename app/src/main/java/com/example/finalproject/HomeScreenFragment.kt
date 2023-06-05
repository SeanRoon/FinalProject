package com.example.finalproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.finalproject.databinding.FragmentHomeScreenBinding
import com.example.finalproject.model.FoodViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class HomeScreenFragment : Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    lateinit var dbRef: DatabaseReference
    private val viewModel: FoodViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        val rootView = binding.root
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        val userEmail = user.email?.substring(0, user.email?.length?.minus(10) ?: 0)
        setHasOptionsMenu(true)
        binding.addFoodButton.setOnClickListener(){
            rootView.findNavController().navigate(R.id.action_homeScreenFragment_to_addFoodFragment)
        }
        binding.resetButton.setOnClickListener(){
            viewModel.reset()
        }
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dbRef.child(userEmail!!).get().addOnSuccessListener {
                    val calsEaten = it.child("caloriesEaten").value.toString().toInt()
                    val totalCals = it.child("totalCalories").value.toString().toInt()
                    binding.caloriesEaten.text = calsEaten.toString()
                    binding.totalCalories.text = totalCals.toString()
                    val totalDub = totalCals.toDouble()
                    val eatenDub = calsEaten.toDouble()
                    if(eatenDub != 0.0) {
                        val progress = eatenDub / totalDub
                        binding.circularProgressIndicator.progress = (progress * 100).toInt()
                        binding.linearProgressIndicator.progress = (progress * 100).toInt()
                    }
                    else
                    {
                        binding.circularProgressIndicator.progress = 0
                        binding.linearProgressIndicator.progress = 0
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MainFragment", "Failed to read value.", error.toException())
            }
        })

        return rootView
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onResume() {
        super.onResume()
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        val userEmail = user.email?.substring(0, user.email?.length?.minus(10) ?: 0)
        dbRef.child(userEmail!!).get().addOnSuccessListener {
            var calsEaten: Int
            var totalCals: Int
            if (it.child("caloriesEaten").exists() and it.child("totalCalories").exists()) {
                calsEaten = it.child("caloriesEaten").value.toString().toInt()
                totalCals = it.child("totalCalories").value.toString().toInt()
                binding.caloriesEaten.text = calsEaten.toString()
                binding.totalCalories.text = totalCals.toString()
                val totalDub = totalCals.toDouble()
                val eatenDub = calsEaten.toDouble()
                if (eatenDub != 0.0) {
                    val progress = eatenDub / totalDub
                    binding.circularProgressIndicator.progress = ((progress * 100)).toInt()
                    binding.linearProgressIndicator.progress = ((progress * 100)).toInt()
                } else {
                    binding.circularProgressIndicator.progress = 0
                    binding.linearProgressIndicator.progress = 0
                }
            }

        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}