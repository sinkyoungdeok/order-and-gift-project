package msa.gift.common.response

import msa.gift.common.exception.GlobalException
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest

@Component
class GlobalErrorAttributes : DefaultErrorAttributes() {

    override fun getErrorAttributes(
        request: ServerRequest?,
        options: ErrorAttributeOptions?
    ): Map<String, Any?> {
        val map: MutableMap<String, Any?> = mutableMapOf()
        val throwable: Throwable = getError(request)
        if (throwable is GlobalException) {
            val ex: GlobalException = getError(request) as GlobalException
            map["result"] = "FAIL"
            map["data"] = null
            map["message"] = ex.reason
            map["errorCode"] = ex.status.reasonPhrase
            return map
        }
        return map
    }
}