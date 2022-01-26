package msa.order.domain.item

import lombok.extern.slf4j.Slf4j
import msa.order.domain.partner.Partner
import msa.order.domain.partner.PartnerReader
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.toMono
import java.util.logging.Logger

@Service
@Slf4j
class ItemServiceImpl(
    val partnerReader: PartnerReader,
    val itemStore: ItemStore,
) : ItemService {
    val log = LoggerFactory.getLogger(javaClass)

    override fun registerItem(
        command: Mono<ItemCommand.RegisterItemRequest>,
        partnerToken: Mono<String>
    ): Mono<ItemInfo.Token> {
        return partnerReader.getPartner(partnerToken)
            .flatMap { p ->
                val map: Mono<Item> = command.map { c ->
                    p.id?.let { c.toEntity(it) } ?: Item(
                            "1",
                            "2",
                            "3",
                            "4",
                            5,
                            null,
                            null
                        )
                }

                map
            }.map {
                ItemInfo.Token(it.itemName)
            }

//        return command
//            .log("test")
//            .flatMap {
//                val temp: Mono<Item> = partnerReader.getPartner(partnerToken)
//                    .log("test3")
//                    .map { it2 ->
//                        it2.id?.let { it1 -> it.toEntity(it1) } ?: Item(
//                            "1",
//                            "2",
//                            "3",
//                            "4",
//                            5,
//                            null,
//                            null
//                        )
//                    }
//                temp
//            }
//            .log("test2")
//            .map {
//                ItemInfo.Token(it.itemName)
//            }

//        return command.log("test")
//            .map {
//                ItemInfo.Token(it.itemName)
//            }
    }
}
