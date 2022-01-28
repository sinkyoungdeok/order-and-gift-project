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
    override fun registerItem(
        command: ItemCommand.RegisterItemRequest,
        partnerToken: String
    ): Mono<ItemInfo.Token> {
        var partner = partnerReader.getPartner(partnerToken)
        var initItem = partner.map { it.id?.let { it1 -> command.toEntity(it1) } }
        var item = initItem.flatMap { itemStore.store(it) }
        return item.map { ItemInfo.Token(it.itemToken) }
    }

    @Transactional
    override fun changeOnSale(itemToken: String): Mono<ItemInfo.Token> {
        var item = itemReader.getItemBy(itemToken)
        return item.flatMap {
            it.changeOnSale()
            itemStore.store(it)
        }.map {
            ItemInfo.Token(it.itemToken)
        }
    }

    @Transactional
    override fun changeEndOfSale(itemToken: String): Mono<ItemInfo.Token> {
        var item = itemReader.getItemBy(itemToken)
        return item.flatMap {
            it.changeEndOfSale()
            itemStore.store(it)
        }.map {
            ItemInfo.Token(it.itemToken)
        }
    }

    @Transactional(readOnly = true)
    override fun retrieveItemInfo(itemToken: String): Mono<ItemInfo.Main> {
        var item = itemReader.getItemBy(itemToken)
        var itemInfo = item.map { itemInfoDtoMapper.of(it) }
        return itemInfo
    }
}
