package msa.order.interfaces.order

import msa.order.common.response.CommonResponse
import msa.order.domain.order.OrderCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/orders")
class OrderApiController(val orderDtoMapper: OrderDtoMapper) {

    @PostMapping("/init")
    fun registerOrder(@RequestBody @Valid request: Mono<OrderDto.RegisterOrderRequest>): Mono<CommonResponse<OrderCommand.RegisterOrder>> {
        val command = request.map{ orderDtoMapper.of(it) }

        return command.map{ CommonResponse(it) }
    }
}