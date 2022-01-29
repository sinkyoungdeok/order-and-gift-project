package msa.order.domain.order

import kotlinx.coroutines.reactor.awaitSingle
import msa.order.domain.item.Item
import msa.order.domain.item.ItemReader
import msa.order.domain.order.item.OrderItem
import msa.order.domain.order.item.OrderItemOption
import msa.order.domain.order.item.OrderItemOptionGroup
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl(
    val orderStore: OrderStore,
    val itemReader: ItemReader
) : OrderService {

    @Transactional
    override suspend fun registerOrder(orderCommand: OrderCommand.RegisterOrder): OrderInfo.Token {
        val initOrder = orderCommand.toEntity()


        initOrder.orderItemList = orderCommand.orderItemList
            .map { commandOrderItem ->
                val item: Item = itemReader.getItemBy(commandOrderItem.itemToken).awaitSingle()
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

        val order: Order = orderStore.store(initOrder).awaitSingle()
        return OrderInfo.Token(order.orderToken)
    }
}