package de.brazill.occursum.main

import android.app.Application
import de.brazill.occursum.models.ContactInterface
import de.brazill.occursum.models.ContactStorageFirebase
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

    lateinit var contacts: ContactInterface

    override fun onCreate() {
        super.onCreate()
        info("Main App Started...")

        //contacts = ContactStorageTemp()
        //contacts = ContactStorageJson(applicationContext)
        contacts = ContactStorageFirebase()
    }
}