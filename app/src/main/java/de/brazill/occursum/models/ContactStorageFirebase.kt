package de.brazill.occursum.models

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ContactStorageFirebase : ContactInterface, AnkoLogger {

    private val db = Firebase.firestore
    lateinit var contacts: MutableList<ContactModel>

    init {
        fetchFirestore()
    }

    private fun fetchFirestore() {
        info("Waiting for firestore...")
        runBlocking {
            try {
                val res = db.collection("contacts").get()

                /* So this bit is rough...
                *
                * For some unknown, and to me unexplainable, reason if I
                * use an await here the code just silently fails and the
                * app freezes. I spend 3 hours on this single line before
                * giving up.
                *
                * THE CURRENT SOLUTION IS REALLY UGLY!!!!!
                *
                * At the moment I just use delay to wait for 750 milliseconds
                * and hope that everything works. It seems to be enough but
                * if it doesn't work, then at least i t wont crash but just
                * return the default value tht is given.
                */
                delay(750L)
                contacts = res.result!!.toObjects<ContactModel>().toMutableList()
            } catch (err: Exception) {
            }
        }
        info("Firestore fetch acknowledged...")
    }

    private fun saveFirestore() {
        contacts.forEach { contact -> runBlocking {db.collection("contacts").document(contact.id).set(contact)}}
    }

    override fun create(contact: ContactModel): Boolean {
        val res = contacts.add(contact.copy())
        saveFirestore()
        return res
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
                saveFirestore()
                return true
            }
        }
        saveFirestore()
        return false
    }

    override fun delete(contact: ContactModel): Boolean {
        val res = contacts.remove(contact)
        saveFirestore()
        return res
    }
}