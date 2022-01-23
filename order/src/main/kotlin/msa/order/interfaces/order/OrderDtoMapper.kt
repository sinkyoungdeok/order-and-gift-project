package msa.order.interfaces.order

import msa.order.domain.order.OrderCommand
import msa.order.domain.order.OrderInfo
import org.mapstruct.*


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface OrderDtoMapper {

    fun of(
        request: OrderDto.RegisterOrderRequest
    ): OrderCommand.RegisterOrder

    fun of(
        request: OrderDto.RegisterOrderItemRequest
    ): OrderCommand.RegisterOrderItem

    fun of(
        request: OrderDto.RegisterOrderItemOptionGroupRequest
    ): OrderCommand.RegisterOrderItemOptionGroup

    fun of(
        request: OrderDto.RegisterOrderItemOptionRequest
    ): OrderCommand.RegisterOrderItemOption

    fun of(
        orderInfo: OrderInfo.Token
    ): OrderDto.RegisterOrderResponse
}