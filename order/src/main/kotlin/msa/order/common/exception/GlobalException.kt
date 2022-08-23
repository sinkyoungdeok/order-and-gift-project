package msa.gift.common.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class GlobalException : ResponseStatusException {
    constructor(status: HttpStatus?) : super(status!!) {}
    constructor(status: HttpStatus?, reason: String?) : super(status!!, reason) {}
    constructor(status: HttpStatus?, reason: String?, cause: Throwable?) : super(
        status!!,
        reason,
        cause
    ) {
    }

    companion object {
        private const val serialVersionUID = -1L
    }
}