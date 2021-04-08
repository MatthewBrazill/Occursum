package de.brazill.occursum.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.brazill.occursum.R
import de.brazill.occursum.main.MainApp
import de.brazill.occursum.models.ContactModel
import kotlinx.android.synthetic.main.activity_create_contact.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class CreateContactActivity: AppCompatActivity(), AnkoLogger {

    var contact = ContactModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("Create Contact Started...")

        setContentView(R.layout.activity_create_contact)
        app = application as MainApp

        new_contact_add_good_topic_info.setOnClickListener {
            toast("Use this field to add conversation topics to the list of good ice-breakers and small talk topics. You can use them to help remember what is and isn't safe to talk about with them.")
        }

        new_contact_add_bad_topic_info.setOnClickListener {
            toast("Use this field to add conversation topics to the list of bad ice-breakers and small talk topics. You can use them to help remember what is and isn't safe to talk about with them.")
        }

        //Create a event handler to handle the 'Create Contact' button
        new_contact_create_button.setOnClickListener {
            contact = ContactModel()

            contact.firstName = first_name.text.toString()
            contact.lastName = last_name.text.toString()
            contact.email = email.text.toString()
            contact.phone = phone.text.toString()

            if (contact.firstName.isNotBlank() && contact.lastName.isNotBlank()) {
                app.contacts.add(contact.copy())
                info("Contact '$contact' added.")
            } else {
                toast("Please complete all the fields.")
            }

            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }
    }
}