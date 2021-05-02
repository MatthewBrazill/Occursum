package de.brazill.occursum.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import de.brazill.occursum.R
import java.io.File
import java.lang.Exception

fun pickImage(parent: Activity, id: Int) {
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_OPEN_DOCUMENT
    intent.addCategory(Intent.CATEGORY_OPENABLE)
    parent.startActivityForResult(
        Intent.createChooser(intent, R.string.select_image.toString()),
        id
    )
}

fun ImageButton.setImageSafe(path: String) {
    try {
        this.setImageBitmap(BitmapFactory.decodeFileDescriptor(context.contentResolver.openFileDescriptor(Uri.parse(path), "r")?.fileDescriptor))
    } catch (err: Exception) {
        this.setImageResource(R.drawable.ic_default_avatar)
    }
    return
}

fun ImageView.setImageSafe(path: String) {
    try {
        this.setImageBitmap(BitmapFactory.decodeFileDescriptor(context.contentResolver.openFileDescriptor(Uri.parse(path), "r")?.fileDescriptor))
    } catch (err: Exception) {
        this.setImageResource(R.drawable.ic_default_avatar)
    }
    return
}