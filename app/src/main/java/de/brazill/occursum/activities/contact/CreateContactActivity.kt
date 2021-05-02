package de.brazill.occursum.activities.contact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.brazill.occursum.R
import de.brazill.occursum.main.MainApp
import de.brazill.occursum.models.ContactModel
import de.brazill.occursum.helpers.*
import kotlinx.android.synthetic.main.activity_create_contact.*
import kotlinx.android.synthetic.main.activity_edit_contact.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class CreateContactActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp
    var contact = ContactModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp
        info("Create Contact Started...")

        setContentView(R.layout.activity_create_contact)

        //Set up the image button with listener to change image and the current/default image
        new_contact_add_img_button.setImageSafe(contact.img)
        new_contact_add_img_button.setOnClickListener {
            pickImage(this, 102)
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            102 /*Image button select an image.*/ -> {
                if (data != null && resultCode == Activity.RESULT_OK) {
                    contact.img = data.data.toString()
                    new_contact_add_img_button.setImageSafe(contact.img)
                    new_contact_add_img_button.refreshDrawableState()
                }
            }
        }
    }
}