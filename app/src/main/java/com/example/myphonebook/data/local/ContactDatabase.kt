package com.example.myphonebook.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myphonebook.data.datamodel.Contact

@Database(entities = [Contact::class], version = 1) //Version ändert sich, wenn sich Schema ändert (neue Tabellen hinzukommen) -> zum Debuggen reicht es die App zu deinstallieren und wieder installieren
abstract class ContactDatabase : RoomDatabase() {

    abstract val contactDatabaseDao: ContactDatabaseDao
}

//ganratieren, dass es nur EINE DB gibt
//threadsicheres Singletonpattern, wo man sichergehen kann, dass man nur 1 DB bekommen
private lateinit var INSTANCE: ContactDatabase         //Instanz, ob es die DB schon gibt oder nicht

//Funktion, um eine DB zu bauen
fun getDatabase(context: Context): ContactDatabase {     //liefert DB zurück
    synchronized(ContactDatabase::class.java) {          //bei Zugriff mehrerer Threads auf DB werden diese synchronisiert. Garantiert, dass nicht 2 Leute geleichzeitig eine DB bauen
        if (!::INSTANCE.isInitialized) {                 //wenn es keine gibt, dann ...
            INSTANCE = Room.databaseBuilder(             //...soll eine gebaut werden
                context.applicationContext,
                ContactDatabase::class.java,
                "my_contacts"                       //Name der DB
            ).build()                                     //und am Ende das ganze bauen
        }
    }
    return INSTANCE
}