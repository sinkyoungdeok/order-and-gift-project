package msa.gift.interfaces.gift.api

import msa.gift.domain.gift.GiftCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface GiftDtoMapper {
    fun of(
        request: GiftDto.RegisterOrderRequest
    ): GiftCommand.RegisterOrder

    fun of(
        request: GiftDto.RegisterOrderItemRequest
    ): GiftCommand.RegisterOrderItem

    fun of(
        request: GiftDto.RegisterOrderItemOptionGroupRequest
    ): GiftCommand.RegisterOrderItemOptionGroup

    fun of(
        request: GiftDto.RegisterOrderItemOptionRequest
    ): GiftCommand.RegisterOrderItemOption
}