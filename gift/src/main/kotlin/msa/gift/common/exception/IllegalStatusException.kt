package msa.gift.common.exception

import msa.gift.common.response.ErrorCode


class IllegalStatusException : BaseException {
    constructor() : super(ErrorCode.COMMON_ILLEGAL_STATUS) {}
    constructor(message: String?) : super(message, ErrorCode.COMMON_ILLEGAL_STATUS) {}
}
