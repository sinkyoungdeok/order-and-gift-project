package msa.order.interfaces.partner

import msa.order.application.partner.PartnerFacade
import msa.order.common.response.CommonResponse
import msa.order.domain.PartnerCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/partners")
class PartnerApiController(val partnerFacade: PartnerFacade) {

    @PostMapping
    fun registerPartner(
        @Valid @RequestBody request: PartnerDto.RegisterRequest
    ): Mono<CommonResponse<PartnerDto.RegisterResponse>> {
        var command: Mono<PartnerCommand.RegisterPartner> = Mono.just(request.toCommand())
        var partnerInfo = partnerFacade.registerPartner(command)
        var response = partnerInfo.map { PartnerDto.RegisterResponse(it) }
        return response.map { CommonResponse(it) }
    }
}