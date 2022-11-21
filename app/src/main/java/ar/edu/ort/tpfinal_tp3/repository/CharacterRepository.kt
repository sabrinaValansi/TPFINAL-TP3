package ar.edu.ort.tpfinal_tp3.repository

import android.content.Context
import androidx.room.Room
import ar.edu.ort.tpfinal_tp3.database.AppDatabase
import ar.edu.ort.tpfinal_tp3.database.dao.CharacterDao


class CharacterRepository private constructor(appDatabase : AppDatabase) {

    private val characterDao : CharacterDao = appDatabase.characterDao()

    suspend fun addCharacter(character: ar.edu.ort.tpfinal_tp3.model.Character) {
        characterDao.insertCharacter(character)
    }

    suspend fun getAllCharacters() : MutableList<ar.edu.ort.tpfinal_tp3.model.Character> {
        return characterDao.getAllCharacters()
    }

    companion object {
        private var characterRepository : CharacterRepository? = null

        fun getInstance(context: Context) : CharacterRepository {
            return characterRepository ?: kotlin.run {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "characters-database"
                ).build()

                val createdCharacterRepo = CharacterRepository(db)
                characterRepository = CharacterRepository(db)
                createdCharacterRepo
            }
        }
    }
}