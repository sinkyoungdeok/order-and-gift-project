package msa.order.interfaces.item

import msa.order.domain.item.ItemCommand
import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface ItemDtoMapper {

    @Mappings(
        Mapping(
            source = "request.itemOptionGroupList",
            target = "itemOptionGroupRequestList"
        )
    )
    fun of(
        request: ItemDto.RegisterItemRequest
    ): ItemCommand.RegisterItemRequest

    @Mappings(
        Mapping(
            source = "itemOptionList",
            target = "itemOptionRequestList"
        )
    )
    fun of(
        request: ItemDto.RegisterItemOptionGroupRequest
    ): ItemCommand.RegisterItemOptionGroupRequest

    fun of(
        request: ItemDto.RegisterItemOptionRequest
    ): ItemCommand.RegisterItemOptionRequest

}