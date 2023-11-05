package application.service.user.endpoint.me

import application.core.extentions.format
import application.service.user.model.User
import application.service.user.model.UserModel
import java.util.*


fun getMeQuery(id: String): String {
    return "select * from \"${UserModel.TABLE_NAME}\" " +
            "where " +
            "${UserModel.COLUMN_ID} = '${id}' " +
            "and " +
            "${UserModel.COLUMN_IS_DELETE} = false"
}

fun deleteMeQuery(id: String): String {
    val query = StringBuilder("update \"${UserModel.TABLE_NAME}\" set ")
    query.append("${UserModel.COLUMN_IS_DELETE} = true, ")
    query.append("${UserModel.COLUMN_TOKEN} = null, ")
    query.append("${UserModel.COLUMN_LOGIN_AT} = null, ")
    query.append("${UserModel.COLUMN_MODIFIED_AT} = '${Date().format()}' ")
    query.append("where ")
    query.append("${UserModel.COLUMN_ID} = '${id}'")
    return query.toString()
}

fun updateMeQuery(user: User): String {
    val query = StringBuilder("update \"${UserModel.TABLE_NAME}\" set ")
    user.name?.let { query.append("${UserModel.COLUMN_NAME} = '$it', ") }
    user.lastname?.let { query.append("${UserModel.COLUMN_NAME} = '$it', ") }
    user.phone?.let { query.append("${UserModel.COLUMN_PHONE} = '$it', ") }
    user.mail?.let { query.append("${UserModel.COLUMN_MAIL} = '$it', ") }
    query.append("${UserModel.COLUMN_MODIFIED_AT} = '${Date().format()}' ")
    query.append("where ")
    query.append("${UserModel.COLUMN_ID} = '${user.id}'")
    return query.toString()
}