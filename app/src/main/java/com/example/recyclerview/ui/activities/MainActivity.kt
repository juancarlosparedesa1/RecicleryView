package com.example.recyclerview.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.data.entities.Users
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.ui.adapters.UsersAdapter

class MainActivity : AppCompatActivity() {

    private var usersList: MutableList<Users> = ArrayList<Users>()

    private lateinit var binding: ActivityMainBinding
    private var usersAdapter = UsersAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initListeners()
    }

    private fun initRecyclerView() {
        binding.rvUsers.adapter = usersAdapter
        binding.rvUsers.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    private fun initListeners() {

        binding.btnInsert.setOnClickListener {
            val us = Users(
                1, "Bayron", "Su profe",
                "https://lh6.googleusercontent.com/proxy/4BdLk6Vc_OzbNQFyeyhdDoCrk-m0OEy-R6xgXjx_k_HKGDa4uD4hu3LoMgY4UXUdZFWRv-Ue-aNgjff8xvVIeQln7eMbkaDHXuRKZff4zls3-44"
            )
            usersList.add(us)

            usersAdapter.listUsers = usersList
            usersAdapter.notifyDataSetChanged()
        }

    }
}