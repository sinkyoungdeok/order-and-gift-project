package msa.gift.interfaces.gift.api

import msa.gift.application.gift.GiftFacade
import msa.gift.common.response.CommonResponse
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/gifts")
class GiftApiController(
    val giftDtoMapper: GiftDtoMapper,
    val giftFacade: GiftFacade
) {
    @PostMapping
    suspend fun registerOrder(
        @RequestBody @Valid request: GiftDto.RegisterOrderRequest
    ): CommonResponse<GiftDto.RegisterOrderResponse> {
        var giftCommand = giftDtoMapper.of(request)
        var giftInfo = giftFacade.registerOrder(giftCommand)
        var response = giftDtoMapper.of(giftInfo)
        return CommonResponse(response)
    }

    @PostMapping("/{giftToken}/payment-processing")
    suspend fun requestPaymentProcessing(@PathVariable giftToken: String): CommonResponse<String> {
        giftFacade.requestPaymentProcessing(giftToken)
        return CommonResponse("OK")
    }

    @PostMapping("/{giftToken}/accept-gift")
    suspend fun acceptGift(
        @PathVariable giftToken: String,
        @RequestBody @Valid request: GiftDto.AcceptGiftRequest
    ): CommonResponse<String> {
        var command = giftDtoMapper.of(giftToken, request)
        giftFacade.acceptGift(command)
        return CommonResponse("OK")
    }
}