package application.service.user.endpoint.logout

import application.core.extentions.format
import application.service.user.model.UserModel
import java.util.*


fun logoutQuery(id: String): String {
    return "update \"${UserModel.TABLE_NAME}\" set " +
            "${UserModel.COLUMN_LOGIN_AT} = null, " +
            "${UserModel.COLUMN_MODIFIED_AT} = '${Date().format()}', " +
            "${UserModel.COLUMN_TOKEN} = null " +
            "where ${UserModel.COLUMN_ID} = '${id}'"
}