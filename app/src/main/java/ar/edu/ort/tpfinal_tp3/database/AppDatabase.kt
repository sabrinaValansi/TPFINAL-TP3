package ar.edu.ort.tpfinal_tp3.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ar.edu.ort.tpfinal_tp3.database.dao.CharacterDao

@Database(entities = [ar.edu.ort.tpfinal_tp3.model.Character::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao() : CharacterDao
}