package com.example.laba3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.laba3.R
import java.security.MessageDigest


class MainActivity : AppCompatActivity() {
    lateinit var emailTextView : TextView
    lateinit var passwordTextView: TextView
    lateinit var submitButton: Button
    lateinit var authError:TextView
    private var userList: Map<String, String> = mapOf(
        "test@test.com" to "65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5", // qwerty
        "admin@test.com" to "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92" // 123456
    )

    private var premiumUsers: Map<String, Boolean> = mapOf(
        "admin@test.com" to true
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailTextView = findViewById(R.id.editTextTextEmailAddress)
        passwordTextView = findViewById(R.id.editTextTextPassword)
        submitButton = findViewById(R.id.submitButton)
        emailTextView.text = "Avadakedavra"
        authError = findViewById(R.id.authError)
        /*submitButton.setOnClickListener {
            val intent = Intent(this,RecyclerActivity::class.java )
            startActivity(intent)
        }*/
        submitButton.setOnClickListener {
            if (authorize(emailTextView.text.toString(), passwordTextView.text.toString()))
            {
                authError.visibility = View.INVISIBLE

                var userPremium = false
                if (this.premiumUsers.contains(emailTextView.text.toString()))
                    userPremium = this.premiumUsers[emailTextView.text.toString()] == true

                val intent = Intent(this, RecyclerActivity::class.java)
                intent.putExtra("USER_PREMIUM", userPremium)
                startActivity(intent)
            }
            else
            {
                authError.visibility = View.VISIBLE
            }
        }
    }

    private fun hashString(input: String): String {
        return MessageDigest.getInstance("SHA-256")
            .digest(input.toByteArray())
            .fold("") { str, it -> str + "%02x".format(it) }
    }

    private fun authorize(email: String, password: String): Boolean
    {
        if (this.userList.contains(email)) {
            return this.userList[email] == hashString(password)
        }
        return false
    }



}