package msa.order.domain.item

import msa.order.domain.partner.PartnerReader
import msa.order.interfaces.item.ItemDtoMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class ItemServiceImpl(
    val partnerReader: PartnerReader,
    val itemStore: ItemStore,
    val itemReader: ItemReader,
    val itemInfoDtoMapper: ItemInfoMapper
) : ItemService {

    @Transactional
    override suspend fun registerItem(
        command: ItemCommand.RegisterItemRequest,
        partnerToken: String
    ): ItemInfo.Token {
        var partner = partnerReader.getPartner(partnerToken)
        var initItem = partner.id?.let { command.toEntity(it) } ?: Item()
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
