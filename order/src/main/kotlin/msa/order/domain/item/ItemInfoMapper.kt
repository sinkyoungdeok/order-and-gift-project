package msa.order.domain.item

import msa.order.domain.item.option.ItemOption
import msa.order.domain.item.optiongroup.ItemOptionGroup
import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface ItemInfoMapper {

    fun of(
        item: Item
    ): ItemInfo.Main

    fun of(
        itemOptionGroup: ItemOptionGroup
    ): ItemInfo.ItemOptionGroupInfo

    fun of(
        itemOption: ItemOption
    ): ItemInfo.ItemOptionInfo
}