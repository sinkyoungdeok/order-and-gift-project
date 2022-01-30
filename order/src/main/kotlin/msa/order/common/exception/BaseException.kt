package msa.order.common.exception

import msa.order.common.response.ErrorCode

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