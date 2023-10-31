package application.service.user.tools

import ignoreNull
import application.service.user.model.User
import java.sql.ResultSet

fun ResultSet.toUser(): User? {
    return try {
        User(
            id = getString("id").ignoreNull(),
            name = getString("name").ignoreNull(),
            lastname = getString("lastname").ignoreNull(),
            username = getString("username").ignoreNull(),
            password = getString("password").ignoreNull(),
            phone = getString("phone"),
            mail = getString("mail"),
            isDelete = getBoolean("is_delete").ignoreNull(),
            createdAt = getString("created_at").ignoreNull(),
            modifiedAt = getString("modified_at"),
            loginAt = getString("login_at"),
            token = getString("token"),
            role = getInt("role")
        )
    } catch (e: Exception) {
        null
    }
}