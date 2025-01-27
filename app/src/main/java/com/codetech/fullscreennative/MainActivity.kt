package com.codetech.fullscreennative

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codetech.fullscreennative.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonShowPortrait.setOnClickListener {
            newScreen(NativeShowActivity::class.java)
        }

        binding.buttonShowPortrait2.setOnClickListener {
            newScreen(NativeShowActivity2::class.java)
        }
    }
}