package com.example.myphonebook

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myphonebook.data.Repository
import com.example.myphonebook.data.datamodel.Contact
import com.example.myphonebook.data.local.getDatabase
import kotlinx.coroutines.launch
import java.lang.Exception

//hier wird statt ViewModel AndroidViewModel verwendet, um den applicationtext zu bekommen
class MainViewModel(application: Application): AndroidViewModel(application) {

    private val database = getDatabase(application) //BD bauen, liefert die korrekte Instanz der DB zurück
    private val repository = Repository(database)   //database ist Zugriffspkt im Repository

    val contactList: LiveData<List<Contact>> = repository.contactList   //ContactListe einfach durchschleusen

    fun insert(contact: Contact) {
        viewModelScope.launch {
            try {
                repository.insert(contact)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error inserting contact: $e")
            }
        }
    }
}