package de.brazill.occursum.models

import org.jetbrains.anko.AnkoLogger

class ContactStorageTemp : ContactInterface, AnkoLogger {

    private val contacts = ArrayList<ContactModel>()

    override fun create(contact: ContactModel): Boolean {
        return contacts.add(contact.copy())
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
                return true
            }
        }
        return false
    }

    override fun delete(contact: ContactModel): Boolean {
        return contacts.remove(contact)
    }
}