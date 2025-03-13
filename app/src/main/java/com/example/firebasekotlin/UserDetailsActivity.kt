package com.example.firebasekotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.firebasekotlin.databinding.ActivityUserDetailsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding
    private lateinit var database: DatabaseReference
    private lateinit var userListAdapter: UserListAdapter
    private var userList: ArrayList<User> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
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

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this@UserDetailsActivity,MainActivity::class.java))
        }

        userListAdapter= UserListAdapter(userList)
        binding.rvUserList.adapter=userListAdapter

        val userDetailsRes = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (userSnap in snapshot.children) {
                    val userData = userSnap.getValue<User>()

                    userData?.let {
                        userList.add(it)
                    }
                }

                // If you have an adapter, notify it here
                userListAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error: ${error.message}")
            }
        }

        database.child("Users").addValueEventListener(userDetailsRes)
    }



}