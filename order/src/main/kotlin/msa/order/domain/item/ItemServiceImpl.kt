package msa.order.domain.item

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemServiceImpl(
    val itemStore: ItemStore,
    val itemReader: ItemReader,
    val itemInfoDtoMapper: ItemInfoMapper
) : ItemService {

    @Transactional
    override suspend fun registerItem(
        command: ItemCommand.RegisterItemRequest,
        partnerName: String
    ): ItemInfo.Token {
        var initItem = command.toEntity(partnerName)
        var item = itemStore.store(initItem)
        return ItemInfo.Token(item.itemToken)
    }

    @Transactional
    override suspend fun changeOnSale(itemToken: String): ItemInfo.Token {
        var item = itemReader.getItemBy(itemToken)
        item.changeOnSale()
        itemStore.store(item)
        return ItemInfo.Token(item.itemToken)
    }

    @Transactional
    override suspend fun changeEndOfSale(itemToken: String): ItemInfo.Token {
        var item = itemReader.getItemBy(itemToken)
        item.changeEndOfSale()
        itemStore.store(item)
        return ItemInfo.Token(item.itemToken)
    }

    @Transactional(readOnly = true)
    override suspend fun retrieveItemInfo(itemToken: String): ItemInfo.Main {
        var item = itemReader.getItemBy(itemToken)
        return itemInfoDtoMapper.of(item)
    }
}
