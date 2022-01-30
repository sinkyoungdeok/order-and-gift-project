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

    fun of(
        request: OrderDto.PaymentRequest
    ): OrderCommand.PaymentRequest

    // retrieve
    @Mappings(
        Mapping(
            source = "orderedAt",
            target = "orderedAt",
            dateFormat = "yyyy-MM-dd HH:mm:ss"
        )
    )
    fun of(
        orderInfo: OrderInfo.Main
    ): OrderDto.Main

    fun of(
        orderInfo: OrderInfo.DeliveryInfo
    ): OrderDto.DeliveryInfo

    fun of(
        orderInfo: OrderInfo.OrderItemInfo
    ): OrderDto.OrderItemInfo

    fun of(
        orderInfo: OrderInfo.OrderItemOptionGroupInfo
    ): OrderDto.OrderItemOptionGroupInfo

    fun of(
        orderInfo: OrderInfo.OrderItemOptionInfo
    ): OrderDto.OrderItemOptionInfo
}