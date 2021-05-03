package de.brazill.occursum.models

class ContactStorageFirebase: ContactInterface {



    override fun create(contact: ContactModel): Boolean {

    }

    override fun find(contactID: String): ContactModel? {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<ContactModel> {
        TODO("Not yet implemented")
    }

    override fun update(contact: ContactModel): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(contact: ContactModel): Boolean {
        TODO("Not yet implemented")
    }
}