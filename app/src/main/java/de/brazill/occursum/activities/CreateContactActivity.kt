package de.brazill.occursum.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.brazill.occursum.R
import kotlinx.android.synthetic.main.activity_create_contact.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class CreateContactActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_contact)

        add_contact_button.setOnClickListener { info("Adding Contact") }
    }
}