package application.service.user.endpoint.check_username

import application.service.user.model.UserModel


fun checkUsernameQuery(username: String): String {
    return "select * from \"${UserModel.TABLE_NAME}\" " +
            "where " +
            "${UserModel.COLUMN_USERNAME} = '${username}'"
}