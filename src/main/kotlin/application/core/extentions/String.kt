package application.core.extentions

fun createId(): String {
    val alphabet = "qwertyuopasdfghjklizxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM0123456789.-_"
    var id = ""
    repeat(30){
        id += alphabet.random()
    }
    return id
}