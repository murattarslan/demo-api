package application.core.text

abstract class Language {
    // error title
    abstract val baseErrorTitle: String
    abstract val sqlErrorTitle: String
    abstract val illegalStateErrorTitle: String
    abstract val illegalArgumentErrorTitle: String
    abstract val classCastErrorTitle: String
    abstract val indexOfBoundErrorTitle: String
    abstract val noSuchElementErrorTitle: String
    abstract val nullPointerErrorTitle: String
    // error message
    abstract val baseErrorMessage: String
    abstract val sqlErrorMessage: String
    abstract val illegalStateErrorMessage: String
    abstract val illegalArgumentErrorMessage: String
    abstract val classCastErrorMessage: String
    abstract val indexOfBoundErrorMessage: String
    abstract val noSuchElementErrorMessage: String
    abstract val nullPointerErrorMessage: String

    // success title
    abstract val getAllCategorySuccessTitle: String
    abstract val getAllUserSuccessTitle: String
    abstract val checkUserSuccessTitle: String
    // success message
    abstract val getAllCategorySuccessMessage: String
    abstract val getAllUserSuccessMessage: String
    abstract val checkUserSuccessMessage: String

    // failure title
    abstract val checkUserWrongPasswordTitle: String
    abstract val checkUserNotFoundTitle: String
    // failure message
    abstract val checkUserWrongPasswordMessage: String
    abstract val checkUserNotFoundMessage: String
}

enum class Locale(val lang: String, val language: Language){
    TURKISH("tr", TR()),
    ENGLISH("en", EN())
}