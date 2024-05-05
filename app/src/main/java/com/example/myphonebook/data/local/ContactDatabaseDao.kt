package com.example.myphonebook.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myphonebook.data.datamodel.Contact

@Dao
interface ContactDatabaseDao {

    //alle Kontakte auslesen
    @Query("SELECT * FROM Contact")
    //alle Kontakte holen
    fun getAll(): LiveData<List<Contact>>

    //einen Kontakt hinzufügen; sollte es schon einen geben, wird er überschrieben
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact)

    //Löschen
    //TODO
}