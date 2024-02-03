package com.test.dm_clases_hc.logic.usercases.jikan

import android.util.Log
import com.example.recyclerview.core.Constants
import com.example.recyclerview.data.network.endpoints.TopAnimeEndPoint
import com.example.recyclerview.data.network.repository.RetrofitBase
import com.example.recyclerview.logic.entities.FullInfoAnimeLG


import com.example.recyclerview.core.getFullInfoAnimeLG


class JikanGetTopAnimesUserCase {

    suspend fun invoke() : Result<List<FullInfoAnimeLG>> {

        var result: Result<List<FullInfoAnimeLG>>? = null
        val  items = ArrayList<FullInfoAnimeLG>()

        try {
            val baseService = RetrofitBase.getRetrofitJikanConnection()
            val service = baseService.create(TopAnimeEndPoint::class.java)
            val call = service.getAllTopAnimes()

            if(call.isSuccessful){
                val infoAnime = call.body()!!
                infoAnime.data.forEach{
                    items.add(it.getFullInfoAnimeLG())
                }
                result = Result.success(items.toList())

            }else{
                Log.e(Constants.TAG, "Error en el llamado del API Jikan")
                result=Result.failure(Exception("Error en el llamado del API Jikan"))
            }
        }catch (ex:Exception){
            Log.e(Constants.TAG, ex.stackTraceToString())
            result= Result.failure(Exception (ex))
        }
        return result!!
    }
}



