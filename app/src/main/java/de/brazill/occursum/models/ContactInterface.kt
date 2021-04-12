package de.brazill.occursum.models

interface ContactInterface {
    fun create(contact: ContactModel): Boolean
    fun find(contactName: String): ContactModel
    fun findAll(): List<ContactModel>
    fun update(oldContact: ContactModel, newContact: ContactModel): Boolean
    fun delete(contact: ContactModel): Boolean
}