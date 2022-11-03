package ar.edu.ort.tpfinal_tp3.service

import ar.edu.ort.tpfinal_tp3.model.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CharacterService {
    @GET("character")
    fun getCharacter(): Call<JsonObject>

}