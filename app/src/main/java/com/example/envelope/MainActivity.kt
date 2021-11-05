package com.example.envelope

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.envelope.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        navController = findNavController(R.id.man_nav_fragment_container)
    }

    override fun onStart() {
        super.onStart()

        setNavControllerListeners()
    }

    private fun setNavControllerListeners() {
        binding?.mainBottomNav?.setOnItemReselectedListener {}
        binding?.mainBottomNav?.setOnItemSelectedListener {
            navController.navigate(it.itemId)
            true
        }
    }

    override fun onStop() {
        super.onStop()

        binding?.mainBottomNav?.setOnItemSelectedListener(null)
        binding?.mainBottomNav?.setOnItemReselectedListener(null)
    }

}