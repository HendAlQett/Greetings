package com.hendalqett.greetingssample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hendalqett.greetings.Greeting
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val greeting = Greeting.Builder(this).setUserName("Hend").setContainsExclamation(true).setTextState(Greeting.TEXT_CAMEL_CASE).build()
        val greetingText = greeting.currentGreeting

        textMain.text = greetingText
    }
}
