package ar.edu.ort.tpfinal_tp3.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CharacterService {
    @GET("character")
    fun getActivity(@Header("api_key") apiKey: String): Call<Character>

}