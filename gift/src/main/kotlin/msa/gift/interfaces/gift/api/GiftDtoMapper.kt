package msa.gift.interfaces.gift.api

import msa.gift.domain.gift.GiftCommand
import msa.gift.domain.gift.GiftInfo
import org.mapstruct.*

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

    fun of(
        giftInfo: GiftInfo.Main
    ): GiftDto.RegisterOrderResponse

    fun of(
        giftToken: String,
        request: GiftDto.AcceptGiftRequest
    ): GiftCommand.AcceptGift
}