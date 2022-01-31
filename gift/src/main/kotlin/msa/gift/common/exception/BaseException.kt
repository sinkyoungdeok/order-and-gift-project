package msa.gift.common.exception

import msa.gift.common.response.ErrorCode

open class BaseException : RuntimeException {
    private var errorCode: ErrorCode? = null

    constructor() {}
    constructor(errorCode: ErrorCode) : super(errorCode.getErrorMsg()) {
        this.errorCode = errorCode
    }

    constructor(message: String?, errorCode: ErrorCode?) : super(message) {
        this.errorCode = errorCode
    }

    constructor(message: String?, errorCode: ErrorCode?, cause: Throwable?) : super(
        message,
        cause
    ) {
        this.errorCode = errorCode
    }
}