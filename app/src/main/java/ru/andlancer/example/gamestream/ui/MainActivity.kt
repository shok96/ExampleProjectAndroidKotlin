package ru.andlancer.example.gamestream.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.andlancer.example.gamestream.R
import ru.andlancer.example.gamestream.databinding.ActivityMainBinding
import ru.andlancer.example.gamestream.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container, MainFragment())
                .commitAllowingStateLoss()
        }
    }
}