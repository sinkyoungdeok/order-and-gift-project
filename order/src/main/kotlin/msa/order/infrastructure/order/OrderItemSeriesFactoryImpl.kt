package msa.order.infrastructure.order

import kotlinx.coroutines.reactor.awaitSingle
import msa.order.domain.item.Item
import msa.order.domain.item.ItemReader
import msa.order.domain.order.Order
import msa.order.domain.order.OrderCommand
import msa.order.domain.order.OrderItemSeriesFactory
import msa.order.domain.order.item.OrderItem
import msa.order.domain.order.item.OrderItemOptionGroup
import msa.order.infrastructure.item.ItemRepository
import org.springframework.stereotype.Component

@Component
class OrderItemSeriesFactoryImpl(
    val itemRepository: ItemRepository,
    val orderRepository: OrderRepository
) : OrderItemSeriesFactory {
    override suspend fun store(initOrder: Order, orderCommand: OrderCommand.RegisterOrder): Order {
        initOrder.orderItemList = orderCommand.orderItemList
            .map { commandOrderItem ->
                val item: Item =
                    itemRepository.findByItemToken(commandOrderItem.itemToken).awaitSingle()
                val initOrderItem: OrderItem = commandOrderItem.toEntity(item)
                initOrderItem.orderItemOptionGroupList =
                    commandOrderItem.orderItemOptionGroupList.map { commandOrderItemOptionGroup ->
                        val initOrderItemOptionGroup: OrderItemOptionGroup =
                            commandOrderItemOptionGroup.toEntity()
                        initOrderItemOptionGroup.orderItemOptionList =
                            commandOrderItemOptionGroup.orderItemOptionList.map { commandOrderItemOption ->
                                commandOrderItemOption.toEntity()
                            }
                        initOrderItemOptionGroup
                    }
                initOrderItem
            }

        return orderRepository.save(initOrder).awaitSingle()
    }
}