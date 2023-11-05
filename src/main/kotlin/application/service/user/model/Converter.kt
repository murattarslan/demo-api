package application.service.user.model

import ignoreNull
import application.service.user.model.User
import java.sql.ResultSet

fun ResultSet.toUser(): User? {
    return try {
        User(
            id = getString(UserModel.COLUMN_ID).ignoreNull(),
            name = getString(UserModel.COLUMN_NAME).ignoreNull(),
            lastname = getString(UserModel.COLUMN_LASTNAME).ignoreNull(),
            username = getString(UserModel.COLUMN_USERNAME).ignoreNull(),
            password = getString(UserModel.COLUMN_PASSWORD).ignoreNull(),
            phone = getString(UserModel.COLUMN_PHONE),
            mail = getString(UserModel.COLUMN_MAIL),
            isDelete = getBoolean(UserModel.COLUMN_IS_DELETE).ignoreNull(),
            createdAt = getString(UserModel.COLUMN_CREATED_AT).ignoreNull(),
            modifiedAt = getString(UserModel.COLUMN_MODIFIED_AT),
            loginAt = getString(UserModel.COLUMN_LOGIN_AT),
            token = getString(UserModel.COLUMN_TOKEN),
            role = getInt(UserModel.COLUMN_ROLE)
        )
    } catch (e: Exception) {
        null
    }
}