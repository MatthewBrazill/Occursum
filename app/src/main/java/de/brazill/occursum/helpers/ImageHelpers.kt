package de.brazill.occursum.helpers

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import de.brazill.occursum.R
import java.io.File

fun pickImage(parent: Activity, id: Int) {

}

fun getImageFromPath(context: Context, path: String): Bitmap? {
    val file = File(path)
    if (file.exists()) {
        return BitmapFactory.decodeFile(file.path)
    }
    return null
}