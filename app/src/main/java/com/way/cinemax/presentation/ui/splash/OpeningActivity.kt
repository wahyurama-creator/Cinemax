package com.way.cinemax.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.way.cinemax.R
import com.way.cinemax.presentation.ui.auth.AuthActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OpeningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)

        lifecycleScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                startActivity(Intent(this@OpeningActivity, AuthActivity::class.java))
                finish()
            }
        }
    }
}