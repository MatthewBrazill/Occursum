package de.brazill.occursum.models

import com.mongodb.*
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import de.brazill.occursum.R
import org.bson.Document
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ContactStorageMongo: ContactInterface, AnkoLogger {

    private val user: String = "app"
    private val key: String = ""//R.string.mongodb_key.toString()

    private val uri: MongoClientURI = MongoClientURI("mongodb+srv://$user:$key@occursum-storage.6zijs.mongodb.net/contacts?retryWrites=true&w=majority")
    private var client: MongoClient = MongoClient(uri)
    private var database: MongoDatabase = client.getDatabase("contacts")
    private var collection: MongoCollection<Document> = database.getCollection("contacts")


    override fun create(contact: ContactModel): Boolean {
        val res = collection.find()
        info(res)
        return true
    }

    override fun find(contactID: String): ContactModel? {
        val res = collection.find()
        info(res)
        return ContactModel()
    }

    override fun findAll(): MutableList<ContactModel> {
        val res = collection.find()
        info(res)
        return emptyList<ContactModel>().toMutableList()
    }

    override fun update(contact: ContactModel): Boolean {
        val res = collection.find()
        info(res)
        return true
    }

    override fun delete(contact: ContactModel): Boolean {
        val res = collection.find()
        info(res)
        return true
    }
}