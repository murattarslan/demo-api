package application.service.user.endpoint.users

import application.service.user.model.UserModel

fun getUserQuery(id: String): String {
    return "select * from \"${UserModel.TABLE_NAME}\" " +
            "where " +
            "${UserModel.COLUMN_ID} = '${id}' " +
            "and " +
            "${UserModel.COLUMN_IS_DELETE} = false"
}

fun getAllUserQuery(): String {
    return "select * from \"${UserModel.TABLE_NAME}\" " +
            "where " +
            "${UserModel.COLUMN_IS_DELETE} = false"
}