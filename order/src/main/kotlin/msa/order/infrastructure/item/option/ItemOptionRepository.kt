package msa.order.infrastructure.item.option

import msa.order.domain.item.option.ItemOption
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ItemOptionRepository : ReactiveMongoRepository<ItemOption, String> {
}