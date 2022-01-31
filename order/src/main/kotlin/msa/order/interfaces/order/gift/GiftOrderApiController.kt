package msa.order.interfaces.order.gift

import msa.order.application.order.OrderFacade
import msa.order.application.order.gift.GiftFacade
import msa.order.common.response.CommonResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/gift-orders")
class GiftOrderApiController(
    val giftOrderDtoMapper: GiftOrderDtoMapper,
    val orderFacade: OrderFacade,
    val giftFacade: GiftFacade
) {
    @PostMapping("/init")
    suspend fun registerOrder(
        @RequestBody @Valid request: GiftOrderDto.RegisterOrderRequest
    ): CommonResponse<GiftOrderDto.RegisterResponse> {
        val orderCommand = giftOrderDtoMapper.of(request)
        val orderInfo = orderFacade.registerOrder(orderCommand)
        val response = giftOrderDtoMapper.of(orderInfo)
        return CommonResponse(response)
    }

    @PostMapping("/payment-order")
    suspend fun paymentOrder(
        @RequestBody @Valid request: GiftOrderDto.PaymentRequest
    ): CommonResponse<String> {
        var orderCommand = giftOrderDtoMapper.of(request)
        giftFacade.paymentOrder(orderCommand)
        return CommonResponse("OK")
    }
}