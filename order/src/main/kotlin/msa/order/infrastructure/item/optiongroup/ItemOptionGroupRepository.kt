package msa.order.infrastructure.item.optiongroup

import msa.order.domain.item.optiongroup.ItemOptionGroup
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ItemOptionGroupRepository : ReactiveMongoRepository<ItemOptionGroup, String> {
}