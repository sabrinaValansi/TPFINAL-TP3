package ar.edu.ort.tpfinal_tp3.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JsonObject (
    @SerializedName("results") val results : List<ar.edu.ort.tpfinal_tp3.model.Character>
        )
    
