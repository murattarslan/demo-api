package application.service.user.endpoint.login

import application.service.user.model.User
import application.service.user.model.UserModel


fun checkLoginQuery(username: String): String {
    return "select * from \"${UserModel.TABLE_NAME}\" " +
            "where " +
            "${UserModel.COLUMN_USERNAME} = '${username}' " +
            "and " +
            "${UserModel.COLUMN_IS_DELETE} = false"
}
fun getLoginQuery(user: User): String {
    return "update \"${UserModel.TABLE_NAME}\" set " +
            "${UserModel.COLUMN_LOGIN_AT} = '${user.loginAt}', " +
            "${UserModel.COLUMN_MODIFIED_AT} = '${user.loginAt}', " +
            "${UserModel.COLUMN_TOKEN} = '${user.token}' " +
            "where ${UserModel.COLUMN_ID} = '${user.id}'"
}