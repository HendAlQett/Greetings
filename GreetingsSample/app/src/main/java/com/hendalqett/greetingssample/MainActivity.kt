package com.hendalqett.greetingssample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val greeting = Greeting.Builder(this).setContainsExclamation(true).setTextState(2).build()
        val greetingText = greeting.currentGreeting

        textMain.text = greetingText
    }
}
