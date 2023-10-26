package restaurant.service.media.endpoint

import io.ktor.http.content.*
import restaurant.core.extentions.createId
import restaurant.core.model.Response
import restaurant.core.model.ResultType
import restaurant.core.plugins.updateQuery
import restaurant.core.text.Locale
import restaurant.service.media.model.Media
import restaurant.service.user.endpoint.registerFailMessage
import restaurant.service.user.endpoint.registerSuccessMessage
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun addMedia(
    part: PartData.FileItem,
    locale: Locale = Locale.TURKISH
): Response<Media> {
    val media = addMedia(part)
    val response = updateQuery(
        "insert into media values (" +
                " '${media.url}'," +
                " '${media.thumb}'," +
                " '${media.id}'," +
                " '${media.type}'," +
                " '${media.mimeType}'," +
                " ${media.width}," +
                " ${media.height}" +
                ")",
        locale,
        registerSuccessMessage(locale)
    )
    return if (response.resultMessage.type == ResultType.ERROR)
        Response(null, response.resultMessage)
    else
        Response(media, registerFailMessage(locale))
}
fun addMedia(part: PartData.FileItem): Media {
    val uploadDir = File("uploads")
    if (uploadDir.exists().not())
        uploadDir.mkdirs()
    val id = createId()

    val fileName = "${id}.${part.originalFileName?.substringAfterLast(".")}"
    val file = File(uploadDir, fileName)
    part.streamProvider().use { input ->
        file.outputStream().buffered().use { output ->
            input.copyTo(output)
        }
    }
    val mimeType = getMimeTypeFromFileName(fileName)
    val type = getFileType(mimeType)
    val (width, height) = getImageDimensions(file)
    val thumbnail = createThumbnail(file, 100, 100)
    val thumbnailFileName = "${id}-thumbnail.jpg"
    val thumbnailFile = File(uploadDir, thumbnailFileName)
    ImageIO.write(thumbnail, "jpg", thumbnailFile)
    return Media(
        id,
        file.absolutePath,
        thumbnailFile.absolutePath,
        type,
        mimeType,
        height,
        width
    )
}

fun getMimeTypeFromFileName(fileName: String): String {
    val ext = fileName.substringAfterLast(".", "")
    return when (ext) {
        "jpg", "jpeg" -> "image/jpeg"
        "png" -> "image/png"
        "pdf" -> "application/pdf"
        else -> "application/*"
    }
}

fun getFileType(mimeType: String): String {
    return when {
        mimeType.startsWith("image") -> "image"
        mimeType.startsWith("video") -> "video"
        mimeType.startsWith("audio") -> "audio"
        else -> "other"
    }
}

fun getImageDimensions(file: File): Pair<Int, Int> {
    val image = ImageIO.read(file)
    val width = image.width
    val height = image.height
    return Pair(width, height)
}

fun createThumbnail(file: File, width: Int, height: Int): BufferedImage {
    val originalImage = ImageIO.read(file)
    val thumbnail = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val g = thumbnail.createGraphics()
    g.drawImage(originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null)
    g.dispose()
    return thumbnail
}