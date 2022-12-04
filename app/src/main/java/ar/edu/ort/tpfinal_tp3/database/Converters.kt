package ar.edu.ort.tpfinal_tp3.database

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import ar.edu.ort.tpfinal_tp3.model.Location
import ar.edu.ort.tpfinal_tp3.model.Origin
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun originToString(origin: Origin) : String = Gson().toJson(origin)

    @TypeConverter
    fun stringToOrigin(string: String) : Origin = Gson().fromJson(string, Origin::class.java)

    @TypeConverter
    fun locationToString(location: Location) : String = Gson().toJson(location)

    @TypeConverter
    fun stringToLocation(string: String) : Location = Gson().fromJson(string, Location::class.java)
}