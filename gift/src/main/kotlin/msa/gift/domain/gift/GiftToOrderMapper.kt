package msa.gift.domain.gift

import msa.gift.domain.gift.order.OrderApiCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface GiftToOrderMapper {
    fun of(
        command: GiftCommand.RegisterOrder
    ): OrderApiCommand.RegisterOrder

    fun of(
        command: GiftCommand.RegisterOrderItem
    ): OrderApiCommand.RegisterOrderItem

    fun of(
        command: GiftCommand.RegisterOrderItemOptionGroup
    ): OrderApiCommand.RegisterOrderItemOptionGroup

    fun of(
        command: GiftCommand.RegisterOrderItemOption
    ): OrderApiCommand.RegisterOrderItemOption
}