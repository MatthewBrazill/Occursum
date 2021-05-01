package de.brazill.occursum.models

interface ContactInterface {
    fun create(contact: ContactModel): Boolean
    fun find(contactID: String): ContactModel?
    fun findAll(): List<ContactModel>
    fun update(contact: ContactModel): Boolean
    fun delete(contact: ContactModel): Boolean
}