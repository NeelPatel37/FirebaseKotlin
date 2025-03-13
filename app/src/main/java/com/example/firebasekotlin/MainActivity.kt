package com.example.firebasekotlin

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.firebasekotlin.databinding.ActivityMainBinding
import com.google.common.base.Objects
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemGestures())
            v.updatePadding(top = insets.top)
            WindowInsetsCompat.CONSUMED
        }
        init()

    }

    private fun init() {

        database = Firebase.database.reference

        binding.ivBack.setOnClickListener {
            finish()
        }

        //write data
        binding.btnDone.setOnClickListener {
            if (isValid()){
                val user = User(
                    binding.edtFirstName.text.toString(),
                    binding.edtLastName.text.toString(),
                    binding.edtPhoneNumber.text.toString(),
                    binding.edtEmail.text.toString()
                )

                database.child("Users").push().setValue(user)

                Toast.makeText(this@MainActivity,"Data inserted successfully",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValid(): Boolean {
        if (binding.edtFirstName.text?.isEmpty() == true) {
            Toast.makeText(this@MainActivity, "Please enter first name ", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.edtFirstName.text?.length!! < 3) {
            Toast.makeText(
                this@MainActivity,
                "Please enter minimum three character",
                Toast.LENGTH_SHORT
            ).show()
        } else if (binding.edtLastName.text?.isEmpty() == true) {
            Toast.makeText(this@MainActivity, "Please enter last name", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.edtPhoneNumber.text?.isEmpty() == true) {
            Toast.makeText(this@MainActivity, "Please enter phone number", Toast.LENGTH_SHORT)
                .show()
            return false
        } else if (binding.edtEmail.text?.isEmpty() == true) {
            Toast.makeText(this@MainActivity, "Please enter email", Toast.LENGTH_SHORT).show()
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches()) {
            Toast.makeText(this@MainActivity, "Please enter valid email", Toast.LENGTH_SHORT).show()
        }

        return true

    }
}