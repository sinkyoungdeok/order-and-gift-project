package msa.order.interfaces.order.gift

import msa.order.domain.order.OrderCommand
import msa.order.domain.order.OrderInfo
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface GiftOrderDtoMapper {

    // init
    @Mapping(source = "buyerUserId", target = "userId")
    fun of(request: GiftOrderDto.RegisterOrderRequest): OrderCommand.RegisterOrder

    fun of(request: GiftOrderDto.RegisterOrderItemRequest): OrderCommand.RegisterOrderItem

    fun of(reqeust: GiftOrderDto.RegisterOrderItemOptionGroupRequest): OrderCommand.RegisterOrderItemOptionGroup

    fun of(request: GiftOrderDto.RegisterOrderItemOptionRequest): OrderCommand.RegisterOrderItemOption

    fun of(orderInfo: OrderInfo.Token): GiftOrderDto.RegisterResponse

    fun of(request: GiftOrderDto.PaymentRequest): OrderCommand.PaymentRequest
}