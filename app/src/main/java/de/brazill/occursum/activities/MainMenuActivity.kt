package de.brazill.occursum.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import de.brazill.occursum.R
import de.brazill.occursum.main.MainApp
import kotlinx.android.synthetic.main.activity_main_menu.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult

class MainMenuActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info("Main Menu Started...")

        setContentView(R.layout.activity_main_menu)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        contact_cards.layoutManager = layoutManager
        contact_cards.adapter = MainMenuAdapter(app.contacts)

        main_menu_add_contact_button.setOnClickListener {
            startActivityForResult<CreateContactActivity>(0)
        }
    }
}