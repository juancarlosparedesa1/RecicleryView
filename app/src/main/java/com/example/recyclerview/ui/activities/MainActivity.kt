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
import com.example.recyclerview.ui.adapters.UsersAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var usersList: MutableList<Users> = ArrayList<Users>()
    private var count: Int = 1

    private lateinit var binding: ActivityMainBinding
    private var usersAdapter = UsersAdapter({ deleteUsers(it) }, { selectUser(it) })
    private var userDiffAdapter = UsersAdapterDiffUtil({ deleteUsersDiff(it) }, { selectUser(it) })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initListeners()
    }

    private fun initRecyclerView() {
        binding.rvUsers.adapter = userDiffAdapter
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
                1, "Bayron $count", "Su profe",
                "https://lh6.googleusercontent.com/proxy/4BdLk6Vc_OzbNQFyeyhdDoCrk-m0OEy-R6xgXjx_k_HKGDa4uD4hu3LoMgY4UXUdZFWRv-Ue-aNgjff8xvVIeQln7eMbkaDHXuRKZff4zls3-44"
            )
            count++
            insertUsersDiff(us)
        }

    }

    private fun insertUsersDiff(us: Users) {
        usersList.add(us)
        userDiffAdapter.submitList(usersList.toList())
    }

    private fun deleteUsersDiff(position: Int) {
        usersList.removeAt(position)
        userDiffAdapter.submitList(usersList.toList())

    }

    private fun insertUsers(us: Users) {
        usersList.add(us)
        usersAdapter.listUsers = usersList
        usersAdapter.notifyItemInserted(usersList.size)
    }

    private fun deleteUsers(position: Int) {
        usersList.removeAt(position)
        usersAdapter.listUsers = usersList
        usersAdapter.notifyItemRemoved(position)
    }

    private fun selectUser(user: Users) {
        Snackbar.make(this, binding.btnInsert, user.name, Snackbar.LENGTH_LONG).show()
    }

}