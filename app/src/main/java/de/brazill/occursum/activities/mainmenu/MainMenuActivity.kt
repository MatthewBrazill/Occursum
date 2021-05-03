package de.brazill.occursum.activities.mainmenu

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import de.brazill.occursum.R
import de.brazill.occursum.activities.contact.CreateContactActivity
import de.brazill.occursum.activities.contact.ViewContactActivity
import de.brazill.occursum.main.MainApp
import de.brazill.occursum.models.ContactModel
import kotlinx.android.synthetic.main.activity_create_contact.view.*
import kotlinx.android.synthetic.main.activity_main_menu.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import java.util.*
import java.util.Locale.filter

class MainMenuActivity : AppCompatActivity(), MainMenuListener, AnkoLogger, SearchView.OnQueryTextListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MainApp
        info("Main Menu Started...")

        setContentView(R.layout.activity_main_menu)

        val layoutManager = LinearLayoutManager(this)
        contact_cards_recycler.layoutManager = layoutManager
        contact_cards_recycler.adapter = MainMenuAdapter(app.contacts.findAll(), this)

        main_menu_add_contact_button.setOnClickListener {
            startActivityForResult(intentFor<CreateContactActivity>(), 1)
        }

        main_menu_searchbar.setOnQueryTextListener(this)
    }

    override fun onCardClick(contact: ContactModel) {
        startActivityForResult(intentFor<ViewContactActivity>().putExtra("contact", contact), 2)
    }

    override fun onQueryTextChange(query: String): Boolean {
        val inserts: MutableList<ContactModel> = emptyList<ContactModel>().toMutableList()
        if (query.isBlank()) {
            inserts.addAll(app.contacts.findAll())
        } else {
            for (contact in app.contacts.findAll()) {
                val name = "${contact.firstName} ${contact.lastName}".toLowerCase(Locale.getDefault())
                if (query.toLowerCase(Locale.getDefault()) in name) {
                    inserts.add(contact)
                }
            }
        }
        contact_cards_recycler.adapter = MainMenuAdapter(inserts, this)
        //contact_cards_recycler.adapter!!.notifyDataSetChanged()
        contact_cards_recycler.scrollToPosition(0)
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        contact_cards_recycler.adapter!!.notifyDataSetChanged()
    }
}