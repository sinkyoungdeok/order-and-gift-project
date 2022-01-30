package msa.order.interfaces.order

import msa.order.application.order.OrderFacade
import msa.order.common.response.CommonResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/orders")
class OrderApiController(
    val orderFacade: OrderFacade,
    val orderDtoMapper: OrderDtoMapper
) {

    @PostMapping("/init")
    suspend fun registerOrder(
        @RequestBody @Valid request: OrderDto.RegisterOrderRequest
    ): CommonResponse<OrderDto.RegisterOrderResponse> {
        val orderCommand = orderDtoMapper.of(request)
        val orderInfo = orderFacade.registerOrder(orderCommand)
        val response = orderDtoMapper.of(orderInfo)
        return CommonResponse(response)
    }

    @PostMapping("/payment-order")
    suspend fun paymentOrder(@RequestBody @Valid paymentRequest: OrderDto.PaymentRequest): CommonResponse<String> {
        var paymentCommand = orderDtoMapper.of(paymentRequest)
        orderFacade.paymentOrder(paymentCommand)
        return CommonResponse("OK")
    }
}