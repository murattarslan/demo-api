fun String?.ignoreNull(default: String = "") = this ?: default

fun Int?.ignoreNull(default: Int = 0) = this ?: default

fun Double?.ignoreNull(default: Double = 0.0) = this ?: default

fun Float?.ignoreNull(default: Float = 0f) = this ?: default

fun Boolean?.ignoreNull(default: Boolean = false) = this ?: default

fun <T>List<T>?.ignoreNull(default: List<T> = listOf()) = this ?: default