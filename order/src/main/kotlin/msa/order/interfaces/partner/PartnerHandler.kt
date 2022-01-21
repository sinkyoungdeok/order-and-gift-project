package msa.order.interfaces.partner

import msa.order.application.partner.PartnerFacade
import msa.order.domain.PartnerCommand
import msa.order.domain.PartnerInfo
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class PartnerHandler(val partnerFacade: PartnerFacade) {
    fun create(serverRequest: ServerRequest): Mono<ServerResponse> {
        var command: Mono<PartnerCommand.RegisterPartner> = serverRequest.bodyToMono()
        var partnerInfo = partnerFacade.registerPartner(command)
        var response = partnerInfo.map {
            partner -> PartnerDto.RegisterResponse(
            partner)
        }

        return ok().body(response)
    }
}