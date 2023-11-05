package application.service.user.endpoint.register

import application.core.extentions.format
import application.service.user.model.User
import application.service.user.model.UserModel
import java.util.*

fun insertRegisterQuery(user: User): String{
    return "insert into \"${UserModel.TABLE_NAME}\" " +
            "(" +
                "${UserModel.COLUMN_ID}, " +
                "${UserModel.COLUMN_USERNAME}, " +
                "${UserModel.COLUMN_LASTNAME}, " +
                "${UserModel.COLUMN_USERNAME}, " +
                "${UserModel.COLUMN_PASSWORD}, " +
                "${UserModel.COLUMN_PHONE}, " +
                "${UserModel.COLUMN_MAIL}, " +
                "${UserModel.COLUMN_IS_DELETE}, " +
                "${UserModel.COLUMN_CREATED_AT}, " +
                "${UserModel.COLUMN_MODIFIED_AT}, " +
                "${UserModel.COLUMN_TOKEN}, " +
                "${UserModel.COLUMN_LOGIN_AT}, " +
                "${UserModel.COLUMN_ROLE} " +
            ")" +
            "values " +
            "(" +
                "'${user.id}', " +
                "'${user.name}', " +
                "'${user.lastname}', " +
                "'${user.username}', " +
                "'${user.password}', " +
                "null, " +
                "null, " +
                "false, " +
                "'${Date().format()}', " +
                "'${Date().format()}', " +
                "'${user.token}', " +
                "'${user.loginAt}', " +
                "${user.role}" +
            ");"
}