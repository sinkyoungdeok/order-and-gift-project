package msa.order.interfaces.partner

import msa.order.application.partner.PartnerFacade
import msa.order.common.response.CommonResponse
import msa.order.domain.partner.PartnerCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/partners")
class PartnerApiController(
    val partnerFacade: PartnerFacade,
    val partnerDtoMapper: PartnerDtoMapper
) {

    @PostMapping
    suspend fun registerPartner(
        @Valid @RequestBody request: PartnerDto.RegisterRequest
    ): CommonResponse<PartnerDto.RegisterResponse> {
        var command: PartnerCommand.RegisterPartner = partnerDtoMapper.of(request)
        var partnerInfo = partnerFacade.registerPartner(command)
        var response = partnerDtoMapper.of(partnerInfo)
        return CommonResponse(response)
    }
}