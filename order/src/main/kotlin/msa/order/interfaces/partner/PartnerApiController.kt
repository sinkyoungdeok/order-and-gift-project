package msa.order.interfaces.partner

import msa.order.application.partner.PartnerFacade
import msa.order.common.response.CommonResponse
import msa.order.domain.partner.PartnerCommand
import msa.order.interfaces.user.UserDtoMapper
import org.springframework.security.access.prepost.PreAuthorize
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
    @PreAuthorize("hasRole('ADMIN')")
    suspend fun registerPartner(
        @Valid @RequestBody request: PartnerDto.RegisterRequest
    ): CommonResponse<PartnerDto.RegisterResponse> {
        var userCommand = partnerDtoMapper.of(request.user)
        var partnerCommand: PartnerCommand.RegisterPartner = partnerDtoMapper.of(request)
        var partnerInfo = partnerFacade.registerPartner(partnerCommand, userCommand)
        var response = partnerDtoMapper.of(partnerInfo)
        return CommonResponse(response)
    }
}