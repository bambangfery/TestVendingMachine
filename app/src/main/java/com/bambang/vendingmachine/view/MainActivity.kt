package com.bambang.vendingmachine.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.bambang.vendingmachine.R
import com.bambang.vendingmachine.databinding.ActivityMainBinding
import com.bambang.vendingmachine.utils.UiHelper

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    companion object {
        lateinit var dialog: AlertDialog
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dialog = UiHelper.progressDialog(this)
        navController = findNavController(R.id.nav_host_fragment)
    }
}