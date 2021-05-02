package de.brazill.occursum.activities.contact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import de.brazill.occursum.R
import de.brazill.occursum.main.MainApp
import de.brazill.occursum.models.ContactModel
import de.brazill.occursum.helpers.*
import kotlinx.android.synthetic.main.activity_edit_contact.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class EditContactActivity : AppCompatActivity(), EditContactListener, AnkoLogger {

    lateinit var app: MainApp
    lateinit var contact: ContactModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp
        contact = intent.extras?.getParcelable("contact")!!
        info("Edit Contact Started...")

        setContentView(R.layout.activity_edit_contact)

        //Set the fields to the current values
        edit_contact_first_name.setText(contact.firstName)
        edit_contact_last_name.setText(contact.lastName)
        edit_contact_email.setText(contact.email)
        edit_contact_phone.setText(contact.phone)

        //Set the image button to the current image
        edit_contact_edit_img_button.setImageSafe(contact.img)



        val likeLayoutManager = LinearLayoutManager(this)
        likes_list_recycler.layoutManager = likeLayoutManager
        likes_list_recycler.adapter = EditContactLikeAdaptor(contact, this)

        val dislikeLayoutManager = LinearLayoutManager(this)
        dislikes_list_recycler.layoutManager = dislikeLayoutManager
        dislikes_list_recycler.adapter = EditContactDislikeAdaptor(contact, this)

        edit_contact_edit_img_button.setOnClickListener {
            pickImage(this, 101)
        }

        edit_contact_save_button.setOnClickListener {
            contact.firstName = edit_contact_first_name.text.toString().toTitleCase()
            contact.lastName = edit_contact_last_name.text.toString().toTitleCase()
            contact.email = edit_contact_email.text.toString()
            contact.phone = edit_contact_phone.text.toString()

            contact.likes = contact.likes
            contact.dislikes = contact.dislikes

            if (!app.contacts.update(contact)) {
                toast("There was an error updating you're contact.")
                setResult(RESULT_CANCELED)
            } else {
                setResult(RESULT_OK)
            }
            finish()
        }

        edit_contact_add_like_button.setOnClickListener {
            if (edit_contact_add_like.text.toString().isNotBlank()) {
                contact.likes.add(edit_contact_add_like.text.toString().toTitleCase())
                edit_contact_add_like.text.clear()
                likes_list_recycler.adapter!!.notifyItemInserted(contact.likes.size)
            } else {
                toast("The Topic can not be empty.")
            }
        }

        edit_contact_add_dislike_button.setOnClickListener {
            if (edit_contact_add_dislike.text.toString().isNotBlank()) {
                contact.dislikes.add(edit_contact_add_dislike.text.toString().toTitleCase())
                edit_contact_add_dislike.text.clear()
                dislikes_list_recycler.adapter!!.notifyItemInserted(contact.dislikes.size)
            } else {
                toast("The Topic can not be empty.")
            }
        }
    }

    override fun onLikeDeleteClick(contact: ContactModel, position: Int) {
        contact.likes.removeAt(position)
        likes_list_recycler.adapter!!.notifyItemRemoved(position)
    }

    override fun onDislikeDeleteClick(contact: ContactModel, position: Int) {
        contact.dislikes.removeAt(position)
        dislikes_list_recycler.adapter!!.notifyItemRemoved(position)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            101 /*Image button select an image.*/ -> {
                if (data != null && resultCode == Activity.RESULT_OK) {
                    contact.img = data.data.toString()
                    edit_contact_edit_img_button.setImageSafe(contact.img)
                    edit_contact_edit_img_button.refreshDrawableState()
                }
            }
        }
    }
}