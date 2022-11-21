package ar.edu.ort.tpfinal_tp3.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert
    suspend fun insertCharacter(character : ar.edu.ort.tpfinal_tp3.model.Character)

    @Query("SELECT * FROM Character")
    suspend fun getAllCharacters() : MutableList<ar.edu.ort.tpfinal_tp3.model.Character>
}