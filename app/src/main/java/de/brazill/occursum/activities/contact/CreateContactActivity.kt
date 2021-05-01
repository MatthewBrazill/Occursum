package de.brazill.occursum.activities.contact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.brazill.occursum.R
import de.brazill.occursum.main.MainApp
import de.brazill.occursum.models.ContactModel
import kotlinx.android.synthetic.main.activity_create_contact.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import java.util.*

class CreateContactActivity : AppCompatActivity(), AnkoLogger {

    var contact = ContactModel()
    lateinit var app: MainApp

    private fun String.toTitleCase(): String {
        val regEx = "(^|\\s)\\w".toRegex()
        return this.replace(regEx) {
            it.value.toUpperCase(Locale.getDefault())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("Create Contact Started...")

        setContentView(R.layout.activity_create_contact)
        app = application as MainApp

        new_contact_add_good_topic_info.setOnClickListener {
            toast("Use this field to add conversation topics to the list of good ice-breakers and small talk topics. You can use them to help remember what is and isn't safe to talk about with them.")
        }
        val likesList = mutableListOf<String>()
        new_contact_add_good_topic_button.setOnClickListener {
            likesList.add(likes.text.toString().toTitleCase())
            likes.text.clear()
        }

        new_contact_add_bad_topic_info.setOnClickListener {
            toast("Use this field to add conversation topics to the list of bad ice-breakers and small talk topics. You can use them to help remember what is and isn't safe to talk about with them.")
        }
        val dislikesList = mutableListOf<String>()
        new_contact_add_bad_topic_button.setOnClickListener {
            dislikesList.add(dislikes.text.toString().toTitleCase())
            dislikes.text.clear()
        }

        //Create a event handler to handle the 'Create Contact' button
        new_contact_create_button.setOnClickListener {
            contact.firstName = first_name.text.toString().toTitleCase()
            contact.lastName = last_name.text.toString().toTitleCase()
            contact.email = email.text.toString()
            contact.phone = phone.text.toString()
            contact.likes = likesList
            contact.dislikes = dislikesList

            if (contact.firstName.isNotBlank() && contact.lastName.isNotBlank()) {
                app.contacts.create(contact.copy())
                info("Contact '$contact' added.")
            } else {
                toast("Please complete all the fields.")
            }

            setResult(RESULT_OK)
            finish()
        }
    }
}