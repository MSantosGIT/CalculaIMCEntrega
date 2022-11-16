package com.example.calculaimcentrega

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculaimcentrega.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenersButton()
    }
    private fun listenersButton(){
        binding.btnIniciar.setOnClickListener{
            teladeCalculo()
        }
    }

    private fun teladeCalculo(){
        val intent =Intent(this, CalculaIMCActivity::class.java)
            startActivity(intent)
    }
}