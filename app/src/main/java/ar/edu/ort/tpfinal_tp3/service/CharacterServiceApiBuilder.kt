package ar.edu.ort.tpfinal_tp3.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CharacterServiceApiBuilder {

    private val BASE_URL = "https://rickandmortyapi.com/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun create(): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }
}