package msa.gift.common.exception

import msa.gift.common.response.ErrorCode


class InvalidTokenException : BaseException {
    constructor() : super(ErrorCode.COMMON_INVALID_PARAMETER) {}
    constructor(errorCode: ErrorCode) : super(errorCode) {}
    constructor(errorMsg: String) : super(errorMsg, ErrorCode.COMMON_INVALID_PARAMETER) {}
    constructor(message: String, errorCode: ErrorCode) : super(message, errorCode) {}
}