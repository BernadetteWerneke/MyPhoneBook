package com.example.myphonebook.data

import androidx.lifecycle.LiveData
import com.example.myphonebook.data.datamodel.Contact
import com.example.myphonebook.data.local.ContactDatabase

const val TAG = "Repository"

class Repository (private val database: ContactDatabase) {   //Konstruktur bekommt DB mit, um späeter auf Test-DB ändern zum Testen

    val contactList: LiveData<List<Contact>> = database.contactDatabaseDao.getAll()

    suspend fun insert(contact: Contact) {
        database.contactDatabaseDao.insert(contact)
    }
}