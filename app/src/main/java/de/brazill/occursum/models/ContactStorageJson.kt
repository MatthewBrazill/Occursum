package de.brazill.occursum.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class ContactStorageJson(context: Context) : ContactInterface {

    private val gson = Gson()
    private val file = File("${context.dataDir}/files/contacts.json")
    private val type = object : TypeToken<MutableList<ContactModel>>() {}.type
    private var contacts = load()

    private fun load(): MutableList<ContactModel> {
        return if (file.exists()) {
            gson.fromJson(file.readText(), type)
        } else {
            file.createNewFile()
            emptyList<ContactModel>().toMutableList()
        }
    }

    private fun save(contacts: MutableList<ContactModel>) {
        file.writeText(gson.toJson(contacts))
    }

    override fun create(contact: ContactModel): Boolean {
        val success = contacts.add(contact)
        save(contacts)
        return success
    }

    override fun find(contactID: String): ContactModel? {
        for (contact in contacts) {
            if (contact.id == contactID) {
                return contact
            }
        }
        return null
    }

    override fun findAll(): MutableList<ContactModel> {
        return contacts
    }

    override fun update(contact: ContactModel): Boolean {
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
        val success = contacts.remove(contact)
        save(contacts)
        return success
    }
}