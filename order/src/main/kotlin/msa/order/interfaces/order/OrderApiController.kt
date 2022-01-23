package msa.order.interfaces.order

import msa.order.application.order.OrderFacade
import msa.order.common.response.CommonResponse
import msa.order.domain.order.OrderInfo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/orders")
class OrderApiController(
    val orderFacade: OrderFacade,
    val orderDtoMapper: OrderDtoMapper
) {

    @PostMapping("/init")
    fun registerOrder(
        @RequestBody @Valid request: Mono<OrderDto.RegisterOrderRequest>
    ): Mono<CommonResponse<OrderInfo.Token>> {
        val orderCommand = request.map { orderDtoMapper.of(it) }
        val orderInfo = orderFacade.registerOrder(orderCommand)

        return orderInfo.map { CommonResponse(it) }
    }
}