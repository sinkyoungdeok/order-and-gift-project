package msa.order.interfaces.order

import msa.order.application.order.OrderFacade
import msa.order.common.response.CommonResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/orders")
class OrderApiController(
    val orderFacade: OrderFacade,
    val orderDtoMapper: OrderDtoMapper
) {

    @PostMapping("/init")
    @PreAuthorize("hasRole('USER')")
    suspend fun registerOrder(
        @RequestBody @Valid request: OrderDto.RegisterOrderRequest,
        principal: Principal
    ): CommonResponse<OrderDto.RegisterOrderResponse> {
        val orderCommand = orderDtoMapper.of(request)
        val orderInfo = orderFacade.registerOrder(orderCommand, principal.name)
        val response = orderDtoMapper.of(orderInfo)
        return CommonResponse(response)
    }

    @PostMapping("/payment-order")
    @PreAuthorize("hasRole('USER')")
    suspend fun paymentOrder(
        @RequestBody @Valid paymentRequest: OrderDto.PaymentRequest
    ): CommonResponse<String> {
        var paymentCommand = orderDtoMapper.of(paymentRequest)
        orderFacade.paymentOrder(paymentCommand)
        return CommonResponse("OK")
    }

    @GetMapping("/{orderToken}")
    @PreAuthorize("hasRole('USER')")
    suspend fun retrieveOrder(
        @PathVariable orderToken: String
    ): CommonResponse<OrderDto.Main> {
        var orderInfo = orderFacade.retrieveOrder(orderToken)
        var response = orderDtoMapper.of(orderInfo)
        return CommonResponse(response)
    }
}