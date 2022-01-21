package msa.order.interfaces.partner

import msa.order.application.partner.PartnerFacade
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux

@Component
class PartnerHandler(val partnerFacade: PartnerFacade) {
    fun create(serverRequest: ServerRequest) = ok().body(partnerFacade.registerPartner())
}