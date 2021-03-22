package de.brazill.occursum.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.brazill.occursum.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainActivity : AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_contact_button.setOnClickListener() {
            info("Adding Contact")
        }
    }
}