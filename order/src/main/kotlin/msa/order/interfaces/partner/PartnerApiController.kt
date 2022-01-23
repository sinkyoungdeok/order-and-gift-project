package msa.order.interfaces.partner

import msa.order.application.partner.PartnerFacade
import msa.order.common.response.CommonResponse
import msa.order.domain.partner.PartnerCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/partners")
class PartnerApiController(val partnerFacade: PartnerFacade, val partnerDtoMapper: PartnerDtoMapper) {

    @PostMapping
    fun registerPartner(
        @Valid @RequestBody request: Mono<PartnerDto.RegisterRequest>
    ): Mono<CommonResponse<PartnerDto.RegisterResponse>> {
        var command: Mono<PartnerCommand.RegisterPartner> = request.map { partnerDtoMapper.of(it) }
        var partnerInfo = partnerFacade.registerPartner(command)
        var response = partnerInfo.map { partnerDtoMapper.of(it) }
        return response.map { CommonResponse(it) }
    }
}