package application.service.user.endpoint.all_users

import application.service.user.model.UserModel
fun getAllUserQueryForAdmin(): String {
    return "select * from \"${UserModel.TABLE_NAME}\" "
}

fun deleteUserQueryForAdmin(id: String, isSafe: Boolean): String {
    return if (isSafe)
        "update \"${UserModel.TABLE_NAME}\" set " +
                "${UserModel.COLUMN_IS_DELETE} = true, " +
                "${UserModel.COLUMN_TOKEN} = null, " +
                "${UserModel.COLUMN_LOGIN_AT} = null " +
                "where " +
                "${UserModel.COLUMN_ID} = '${id}'"
    else
        "delete from \"${UserModel.TABLE_NAME}\" " +
            "where " +
            "${UserModel.COLUMN_ID} = '${id}'"
}