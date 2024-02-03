package com.example.recyclerview.logic.usercases.jikan

import com.example.recyclerview.logic.entities.FullInfoAnimeLG

class JikanAnimeUserCase {


    fun getAnimeFullInformation(nameAnime:Integer): FullInfoAnimeLG {

/*

        var infoAnime = ArrayList<TopAnimes>()
        try {
            val baseService = RetrofitBase.getRetrofitJikanConnection()
            val service = baseService.create(AnimeEndPoint::class.java)
            val call = service.getAnimeFullInfo()

            if(call.isSuccessful){
                val a = call.body()!!
                infoAnime.addAll(a)
            }else{
                Log.d(Constants.TAG, "Error en el llamado del API Jikan")
            }
        }catch (ex:Exception){
            Log.e(Constants.TAG, ex.stackTraceToString())
        }
        return infoAnime
        */

        return FullInfoAnimeLG()

        }





    fun getAllTopsAnimes(){

    }

    }



