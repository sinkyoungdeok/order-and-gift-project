package msa.order.interfaces.partner

import msa.order.application.partner.PartnerFacade
import msa.order.common.response.CommonResponse
import msa.order.domain.partner.PartnerCommand
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono

@Component
class PartnerHandler(val partnerFacade: PartnerFacade, val partnerDtoMapper: PartnerDtoMapper) {
    fun create(serverRequest: ServerRequest): Mono<ServerResponse> {
        var command: Mono<PartnerCommand.RegisterPartner> =
            serverRequest.bodyToMono<PartnerDto.RegisterRequest>().map { partnerDtoMapper.of(it) }
        var partnerInfo = partnerFacade.registerPartner(command)
        var response = partnerInfo.map { partnerDtoMapper.of(it) }
        return ok().body(response.map { CommonResponse(it) })
    }
}