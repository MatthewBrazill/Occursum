package de.brazill.occursum.activities.editcontact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import de.brazill.occursum.R
import de.brazill.occursum.activities.ViewContactActivity
import de.brazill.occursum.main.MainApp
import de.brazill.occursum.models.ContactModel
import kotlinx.android.synthetic.main.activity_edit_contact.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import java.lang.Exception

class EditContactActivity : AppCompatActivity(), EditContactListener, AnkoLogger {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("Edit Contact Started...")

        val contact = intent.extras?.getParcelable<ContactModel>("contact")!!

        setContentView(R.layout.activity_edit_contact)
        app = application as MainApp

        edit_contact_first_name.hint = contact.firstName
        edit_contact_last_name.hint = contact.lastName
        edit_contact_email.hint = contact.email
        edit_contact_phone.hint = contact.phone

        val layoutManager = LinearLayoutManager(this)
        likes_list_recycler.layoutManager = layoutManager
        likes_list_recycler.adapter = EditContactAdaptor(app.contacts.findAll(), this)

        /*
        dislikes_list_recycler.layoutManager = layoutManager
        dislikes_list_recycler.adapter = EditContactAdaptor(app.contacts.findAll(), this)
        */
    }

    override fun onLikeDeleteClick(contact: ContactModel, position: Int) {
        contact.likes.removeAt(position)
    }
}