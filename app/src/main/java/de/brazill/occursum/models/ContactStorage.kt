package de.brazill.occursum.models

import java.lang.Exception

class ContactStorage: ContactInterface {

    private val contacts = ArrayList<ContactModel>()

    override fun create(contact: ContactModel): Boolean {
        try {
            contacts.add(contact.copy())
        } catch (err: Exception) {
            return false
        }
        return true
    }

    override fun find(contactName: String): ContactModel {
        for (contact in contacts) {
            if ("${contact.firstName} ${contact.lastName}" == contactName) {
                return contact
            }
        }
        return ContactModel()
    }

    override fun findAll(): List<ContactModel> {
        return contacts
    }

    override fun update(oldContact: ContactModel, newContact: ContactModel): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(contact: ContactModel): Boolean {
        try {
            contacts.remove(contact)
        } catch (e: Exception) {
            return false
        }
        return true
    }
}