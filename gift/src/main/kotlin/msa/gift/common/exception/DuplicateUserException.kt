package msa.gift.common.exception

import msa.gift.common.response.ErrorCode

class DuplicateUserException : BaseException {
    constructor() : super(ErrorCode.DUPLICATE_USER) {}
    constructor(errorCode: ErrorCode) : super(errorCode) {}
    constructor(errorMsg: String) : super(errorMsg, ErrorCode.DUPLICATE_USER) {}
    constructor(message: String, errorCode: ErrorCode) : super(message, errorCode) {}
}