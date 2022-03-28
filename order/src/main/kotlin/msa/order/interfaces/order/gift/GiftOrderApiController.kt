package msa.order.interfaces.order.gift

import msa.order.application.order.OrderFacade
import msa.order.application.order.gift.GiftFacade
import msa.order.common.response.CommonResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/gift-orders")
class GiftOrderApiController(
    val giftOrderDtoMapper: GiftOrderDtoMapper,
    val orderFacade: OrderFacade,
    val giftFacade: GiftFacade
) {
    @PostMapping("/init")
    @PreAuthorize("hasRole('ADMIN')")
    suspend fun registerOrder(
        @RequestBody @Valid request: GiftOrderDto.RegisterOrderRequest
    ): CommonResponse<GiftOrderDto.RegisterResponse> {
        val orderCommand = giftOrderDtoMapper.of(request)
        val orderInfo = orderFacade.registerOrder(orderCommand, "")
        val response = giftOrderDtoMapper.of(orderInfo)
        return CommonResponse(response)
    }

    @PostMapping("/payment-order")
    @PreAuthorize("hasRole('ADMIN')")
    suspend fun paymentOrder(
        @RequestBody @Valid request: GiftOrderDto.PaymentRequest
    ): CommonResponse<String> {
        var orderCommand = giftOrderDtoMapper.of(request)
        giftFacade.paymentOrder(orderCommand)
        return CommonResponse("OK")
    }

    @PostMapping("/{orderToken}/update-receiver-info")
    @PreAuthorize("hasRole('ADMIN')")
    suspend fun updateReceiverInfo(
        @PathVariable orderToken: String,
        @RequestBody @Valid request: GiftOrderDto.UpdateReceiverInfoRequest
    ): CommonResponse<String> {
        var orderCommand = giftOrderDtoMapper.of(request)
        orderFacade.updateReceiverInfo(orderToken, orderCommand)
        return CommonResponse("OK")
    }
}