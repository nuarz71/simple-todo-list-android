package io.github.nuarz71.todolist.android.utils.extension


inline fun String?.ifNotBlank(block: (String) -> Unit) {
    this?.let {
        if (it.isNotBlank()) {
            block(it)
        }
    }
}