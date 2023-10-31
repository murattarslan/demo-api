package application.core.model

enum class ResultType(val text: String) {
    SUCCESS("success"),
    FAILURE("failure"),
    ERROR("error"),
    INFO("info"),
}