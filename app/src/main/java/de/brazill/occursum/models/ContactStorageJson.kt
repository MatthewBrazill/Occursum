package de.brazill.occursum.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.io.File

class ContactStorageJson(context: Context) : ContactInterface, AnkoLogger {

    private val gson = Gson()
    private val fileName = "$context/contacts.json"
    private val type = object : TypeToken<MutableList<ContactModel>>() {}.type

    private fun load(): MutableList<ContactModel> {
        if (File(fileName).exists()) {
            return gson.fromJson(File(fileName).readText(), type)
        }
        return mutableListOf(ContactModel())
    }

    private fun save(contacts: MutableList<ContactModel>) {
        File(fileName).writeText(gson.toJson(contacts))
    }

    override fun create(contact: ContactModel): Boolean {
        var contacts = load()
        val success = contacts.add(contact)
        save(contacts)
        return success
    }

    override fun find(contactID: String): ContactModel? {
        var contacts = load()
        for (contact in contacts) {
            if (contact.id == contactID) {
                return contact
            }
        }
        return null
    }

    override fun findAll(): List<ContactModel> {
        return load()
    }

    override fun update(contact: ContactModel): Boolean {
        var contacts = load()
        for (i in contacts.indices) {
            if (contacts[i].id == contact.id) {
                contacts[i] = contact
                save(contacts)
                return true
            }
        }
        return false
    }

    override fun delete(contact: ContactModel): Boolean {
        var contacts = load()
        val success = contacts.remove(contact)
        save(contacts)
        return success
    }
}