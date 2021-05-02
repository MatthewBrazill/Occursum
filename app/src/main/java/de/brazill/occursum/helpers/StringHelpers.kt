package de.brazill.occursum.helpers

import java.util.*

fun String.toTitleCase(): String {
    val regEx = "(^|\\s)\\w".toRegex()
    return this.replace(regEx) {
        it.value.toUpperCase(Locale.getDefault())
    }
}