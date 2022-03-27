package msa.gift.common.response

import msa.gift.common.exception.InvalidPasswordException
import msa.gift.common.exception.InvalidTokenException
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.server.ServerWebInputException
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
            errors.toString(),
            ErrorCode.COMMON_INVALID_PARAMETER
        )

        return Mono.just(errorResponse)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [ServerWebInputException::class])
    fun methodArgumentNotValidException(e: ServerWebInputException): Mono<CommonResponse<Nothing?>> {
        val errorResponse = CommonResponse(
            null,
            ErrorCode.COMMON_INVALID_PARAMETER
        )

        return Mono.just(errorResponse)
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = [InvalidPasswordException::class])
    fun invalidPasswordException(e: InvalidPasswordException): Mono<CommonResponse<Nothing?>> {
        val errorResponse = CommonResponse(null, ErrorCode.INVALID_PASSWORD_EXCEPTION)

        return Mono.just(errorResponse)
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = [InvalidTokenException::class])
    fun invalidTokenException(e: InvalidTokenException): Mono<CommonResponse<Nothing?>> {
        val errorResponse = CommonResponse(null, ErrorCode.INVALID_TOKEN_EXCEPTION)

        return Mono.just(errorResponse)
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = [AccessDeniedException::class])
    fun accessDeniedException(e: AccessDeniedException): Mono<CommonResponse<Nothing?>> {
        val errorResponse = CommonResponse(null,ErrorCode.ACCESS_DENIED)

        return Mono.just(errorResponse)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [Exception::class])
    fun exception(e: Exception): Mono<CommonResponse<String>> {
        val errorResponse = CommonResponse(e.toString(),ErrorCode.COMMON_SYSTEM_ERROR)

        return Mono.just(errorResponse)
    }
}