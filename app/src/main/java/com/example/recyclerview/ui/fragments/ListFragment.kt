package com.example.recyclerview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.R
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.databinding.FragmentDetailBinding
import com.example.recyclerview.databinding.FragmentListBinding
import com.example.recyclerview.logic.entities.FullInfoAnimeLG
import com.example.recyclerview.ui.adapters.UsersAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar
import com.test.dm_clases_hc.logic.usercases.jikan.JikanGetTopAnimesUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//class ListFragment : Fragment() {
//    private lateinit var binding: FragmentListBinding
//    private var usersList: MutableList<FullInfoAnimeLG> = ArrayList()
//    private var userDiffAdapter = UsersAdapterDiffUtil({ deleteUsersDiff(it) }, { selectAnime(it) })
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//       binding=FragmentListBinding.bind( inflater.inflate(R.layout.fragment_list, container, false))
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initRecyclerView()
//    }
//    private fun initRecyclerView() {
//        binding.rvUsers.adapter = userDiffAdapter
//        binding.rvUsers.layoutManager =
//            LinearLayoutManager(
//                requireActivity(),
//                LinearLayoutManager.VERTICAL,
//                false
//            )
//        loadDataRecyclerView()
//    }
//
//    private fun loadDataRecyclerView()
//    {
//
//        TODO("LLamado a la api")
//        lifecycleScope.launch(Dispatchers.Main) {
//            binding.progresBar.visibility = View.VISIBLE
//
//            val resp = withContext(Dispatchers.IO) {
//                JikanGetTopAnimesUserCase().invoke()
//            }
//            resp.onSuccess { listAnime ->
//                usersList.addAll(listAnime)
//                insertUsersDiff(usersList)
//            }
//            resp.onFailure {ex->
//                Snackbar.make(requireActivity(),
//                    binding.rvUsers,ex.message.toString(),
//                    Snackbar.LENGTH_LONG).show()
//            }
//            binding.progresBar.visibility = View.GONE
//        }
//    }
//    private fun insertUsersDiff(animes: List<FullInfoAnimeLG>) {
//        usersList.addAll(animes)
//        userDiffAdapter.submitList(usersList.toList())
//    }
//
//    private fun deleteUsersDiff(position: Int) {
//        usersList.removeAt(position)
//        userDiffAdapter.submitList(usersList.toList())
//
//    }
//
//    private fun selectAnime(anime: FullInfoAnimeLG) {
//    findNavController().navigate(R.id.action_listFragment_to_detailFragment)
//    }
//}
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    // initRecycleView()
    private var userList: MutableList<FullInfoAnimeLG> = ArrayList()
    private var userDiffAdapter= UsersAdapterDiffUtil({deleteUsersDiff(it)},{selectAnime(it)})

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentListBinding.bind(inflater.inflate(R.layout.fragment_list, container, false))
        return binding.root
    }


    ///migracion de main hacia el fragment
    fun initRecycleView() {

        binding.rvUsers.adapter = userDiffAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL, false
        )

        loadDataRecyclerView()


    }


    private fun loadDataRecyclerView(){

        //llamdo a la api jikan

        lifecycleScope.launch(Dispatchers.Main) {
            binding.progresBar.visibility=View.VISIBLE
            val resp= withContext(Dispatchers.IO){
                JikanGetTopAnimesUserCase().invoke()
            }
            resp.onSuccess { listAnime ->
                userList.addAll(listAnime)
                insertUsersDiff(userList)
            }

            resp.onFailure {ex->
                Snackbar.make(requireActivity(), binding.rvUsers, ex.message.toString(), Snackbar.LENGTH_LONG).show()
            }
            binding.progresBar.visibility=View.GONE
        }

    }




    private fun insertUsersDiff(animes: List<FullInfoAnimeLG>) {
        userList.addAll(animes)
        userDiffAdapter.submitList(userList.toList())

    }


    private fun deleteUsersDiff(position : Int){
        userList.removeAt(position)
        userDiffAdapter.submitList(userList.toList())

    }




    private  fun selectAnime(anime: FullInfoAnimeLG){
        //Snackbar.make(requireActivity(), binding.rvUsers, anime.name, Snackbar.LENGTH_LONG).show()
        //findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(idAnime=anime.id))


    }






}