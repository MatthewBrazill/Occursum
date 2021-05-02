package de.brazill.occursum.activities.mainmenu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import de.brazill.occursum.R
import de.brazill.occursum.activities.contact.CreateContactActivity
import de.brazill.occursum.activities.contact.ViewContactActivity
import de.brazill.occursum.main.MainApp
import de.brazill.occursum.models.ContactModel
import kotlinx.android.synthetic.main.activity_main_menu.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import java.util.*

class MainMenuActivity : AppCompatActivity(), MainMenuListener, AnkoLogger {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("Main Menu Started...")

        setContentView(R.layout.activity_main_menu)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        contact_cards_recycler.layoutManager = layoutManager
        contact_cards_recycler.adapter = MainMenuAdapter(app.contacts.findAll(), this)

        main_menu_add_contact_button.setOnClickListener {
            startActivityForResult(intentFor<CreateContactActivity>(),0)
        }
    }

    override fun onCardClick(contact: ContactModel) {
        startActivityForResult(intentFor<ViewContactActivity>().putExtra("contact", contact),0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        contact_cards_recycler.adapter!!.notifyDataSetChanged()
    }
}