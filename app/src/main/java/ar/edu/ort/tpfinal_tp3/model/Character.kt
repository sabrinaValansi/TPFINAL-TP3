package ar.edu.ort.tpfinal_tp3.model
import com.google.gson.annotations.SerializedName

data class Character (
    @SerializedName(value = "character") val name: String?,
    val type: String?,
    val participants: Int?,

)