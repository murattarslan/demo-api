package application.service.media.tools

import ignoreNull
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import application.core.extentions.lang
import application.core.model.Response
import application.core.model.ResultMessage
import application.service.media.endpoint.addMedia
import application.service.media.model.Media
import java.sql.ResultSet

fun ResultSet.toMedia(): Media? {
    return try {
        Media(
            id = getString("id").ignoreNull(),
            url = getString("url").ignoreNull(),
            thumb = getString("thumb").ignoreNull(),
            type = getString("type").ignoreNull(),
            mimeType = getString("mimeType").ignoreNull(),
            height = getInt("height").ignoreNull(),
            width = getInt("width").ignoreNull()
        )
    } catch (e: Exception) {
        null
    }
}

suspend fun PipelineContext<Unit, ApplicationCall>.handleFileUpload(): ArrayList<Media> {
    val medias: ArrayList<Media> = arrayListOf()
    call.receiveMultipart().forEachPart { part ->
        when (part) {
            is PartData.FileItem -> {
                val response = addMedia(part,lang())
                if (response.result != null)
                    medias.add(response.result!!)
                else{
                    call.respond(response)
                    return@forEachPart
                }
            }
            else -> {}
        }
        part.dispose()
    }
    call.respond(Response(medias, ResultMessage()))
    return medias
}