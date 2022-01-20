package msa.order.application.partner

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux

@Component
class PartnerHandler {
    fun create(serverRequest: ServerRequest) = ok().body(Flux.just("test"))
}