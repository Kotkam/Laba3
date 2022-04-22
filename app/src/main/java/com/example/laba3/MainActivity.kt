package com.example.laba3

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.laba3.Models.User
import com.example.laba3.R
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.security.MessageDigest
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var emailTextView : TextView
    lateinit var passwordTextView: TextView
    lateinit var submitButton: Button
    lateinit var authError:TextView
    private lateinit var database: FirebaseFirestore
    private lateinit var database1: FirebaseDatabase

    private var maxId : Int = 0
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
        emailTextView.text = "test@mail.com"
        passwordTextView.text="qwerty"
        authError = findViewById(R.id.authError)
        database = Firebase.firestore
        setUpDatabase()
        /*submitButton.setOnClickListener {
            val intent = Intent(this,RecyclerActivity::class.java )
            startActivity(intent)
        }*/
        submitButton.setOnClickListener {
            val docRef = database.collection("users").document(emailTextView.text.toString())
            docRef.get().addOnSuccessListener {
                if(it != null){
                    val user = it.toObject(User::class.java)

                    if (user?.password == hashString(passwordTextView.text.toString()))
                    {
                        authError.visibility = View.INVISIBLE

                        val userPremium = user?.role == 1
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
        }
    }

    private fun hashString(input: String): String {
        return MessageDigest.getInstance("SHA-256")
            .digest(input.toByteArray())
            .fold("") { str, it -> str + "%02x".format(it) }
    }

    private fun setUpDatabase(){

        //database1 = Firebase.database("https://laba3-lmassv-default-rtdb.europe-west1.firebasedatabase.app")
        //val myRef = database1.reference


        var user = User()
        user.username = "test@mail.com"
        user.password = "65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5"
        var user2 = User()
        user2.username = "admin@mail.com"
        user2.password = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92"
        user2.role=1
        database.collection("users").document(user.username!!).set(user)
        database.collection("users").document(user2.username!!).set(user2)
        //myRef.child("Users").child(user.username.toString()).setValue(user)
        //myRef.child("Users").child(user2.username).setValue(user2)
        /*val data = hashMapOf(
            "user_id" to 1,
            "name" to "test@test.com",
            "password" to "65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5",
            "role" to "pleb"
        )*/
       // database.collection("database").document("users").set(data)

    }
    /*private fun authorize(email: String, password: String): Boolean
    {
        val docRef = database.collection("users").document(email)
        var result :Boolean = false
        docRef.get().addOnSuccessListener {
            result = true
            if(it != null){
                result = true
                user = it.toObject<User>()!!
            }
            else{
                result = false
            }
        }
        return result
    }*/



}