package msa.order.common.response

import msa.order.common.exception.InvalidPasswordException
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException
import reactor.core.publisher.Mono

@RestControllerAdvice
class CommonControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [WebExchangeBindException::class])
    fun methodArgumentNotValidException(e: WebExchangeBindException): Mono<CommonResponse<String>> {
        val errors = mutableListOf<Error>()

        e.allErrors.forEach {
            val error = Error().apply {
                this.field = (it as FieldError).field
                this.message = it.defaultMessage
                this.value = it.rejectedValue
            }
            errors.add(error)
        }

        val errorResponse = CommonResponse(
            CommonResponse.Result.FAIL,
            "",
            errors.toString(),
            HttpStatus.BAD_REQUEST.value().toString()
        )

        return Mono.just(errorResponse)
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = [InvalidPasswordException::class])
    fun invalidPasswordException(e: InvalidPasswordException): Mono<CommonResponse<String>> {
        val errorResponse = CommonResponse(
            CommonResponse.Result.FAIL,
            "",
            e.message.toString(),
            HttpStatus.UNAUTHORIZED.value().toString()
        )

        return Mono.just(errorResponse)
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = [AccessDeniedException::class])
    fun accessDeniedException(e: AccessDeniedException): Mono<CommonResponse<String>> {
        val errorResponse = CommonResponse(
            CommonResponse.Result.FAIL,
            "",
            "접근 권한이 없습니다.",
            HttpStatus.FORBIDDEN.value().toString()
        )

        return Mono.just(errorResponse)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [Exception::class])
    fun exception(e: Exception): Mono<CommonResponse<String>> {

        val errorResponse = CommonResponse(
            CommonResponse.Result.FAIL,
            "",
            e.toString(),
            HttpStatus.BAD_REQUEST.value().toString()
        )

        return Mono.just(errorResponse)
    }
}