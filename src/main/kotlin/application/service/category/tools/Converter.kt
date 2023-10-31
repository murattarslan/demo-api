package application.service.category.tools

import ignoreNull
import application.service.category.model.Category
import java.sql.ResultSet

fun ResultSet.toCategory(): Category? {
    return try {
        Category(
            id = getString("id").ignoreNull(),
            name = getString("title").ignoreNull(),
            createdAt = getString("created_at").ignoreNull(),
            modifiedAt = getString("modified_at").ignoreNull()
        )
    } catch (e: Exception) {
        null
    }
}