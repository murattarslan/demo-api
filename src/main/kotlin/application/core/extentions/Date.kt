package application.core.extentions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    return SimpleDateFormat(pattern, Locale("tr")).format(this)
}

fun String.toDate(pattern: String = "yyyy-MM-dd HH:mm:ss"): Date {
    return try {
        SimpleDateFormat(pattern, Locale("tr")).parse(this)
    } catch (e: Exception) {
        Date()
    }
}