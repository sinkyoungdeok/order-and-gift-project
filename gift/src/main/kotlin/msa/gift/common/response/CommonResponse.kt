package msa.gift.common.response

data class CommonResponse<T>(
    var result: Result,
    var data: T,
    var message: String,
    var errorCode: String?
) {
    constructor(data: T, message: String) : this(Result.SUCCESS, data, message, null)
    constructor(data: T) : this(Result.SUCCESS, data, "", null)
    constructor(data: T, errorCode: ErrorCode) : this(Result.FAIL,
        data,
        errorCode.errorMsg,
        errorCode.name)

    enum class Result {
        SUCCESS, FAIL
    }
}

data class Error(
    var field: String? = null,
    var message: String? = null,
    var value: Any? = null
)