package de.brazill.occursum.utilities

class AppUtilities {
    fun String.toTitleCase(): String {
        val regEx = "\\s[\\w]".toRegex()
        regEx.replace(this) {it.value.toUpperCase()}
        return this
    }
}