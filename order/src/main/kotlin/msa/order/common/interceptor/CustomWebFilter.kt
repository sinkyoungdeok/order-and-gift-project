package msa.order.common.interceptor

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import reactor.core.publisher.Signal
import reactor.util.context.Context
import java.util.*
import java.util.function.Consumer


@Component
class RequestIdFilter : WebFilter {

    val log = LoggerFactory.getLogger(RequestIdFilter::class.java)

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request = exchange.request
        val requestId = getRequestId(request.headers)
        return chain
            .filter(exchange)
            .doOnEach(logOnEach(Consumer { r: Void? ->
                log.info(
                    "{} {}",
                    request.method,
                    request.uri
                )
            }))
            .contextWrite(Context.of("CONTEXT_KEY", requestId))
    }

    private fun getRequestId(headers: HttpHeaders): String {
        val requestIdHeaders = headers["X-Request-ID"]
        return if (requestIdHeaders == null || requestIdHeaders.isEmpty()) UUID.randomUUID()
            .toString() else requestIdHeaders[0]
    }

    companion object {
        fun <T> logOnEach(logStatement: Consumer<T?>): Consumer<Signal<T>> {
            return Consumer { signal: Signal<T> ->
                val contextValue =
                    signal.contextView.get<String>("CONTEXT_KEY")
                MDC.putCloseable("MDC_KEY", contextValue).use { cMdc ->
                    logStatement.accept(
                        signal.get()
                    )
                }
            }
        }
    }
}
