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


        val orderItemList = orderCommand.orderItemList
            .map { commandOrderItem ->
                val item: Item = itemReader.getItemBy(commandOrderItem.itemToken).awaitSingle()
                val initOrderItem: OrderItem = commandOrderItem.toEntity(item)

                val orderItemOptionGroupList: List<OrderItemOptionGroup> =
                    commandOrderItem.orderItemOptionGroupList.map { commandOrderItemOptionGroup ->
                        val initOrderItemOptionGroup: OrderItemOptionGroup =
                            commandOrderItemOptionGroup.toEntity()

                        val orderItemOptionList: List<OrderItemOption> =
                            commandOrderItemOptionGroup.orderItemOptionList.map { commandOrderItemOption ->
                                val initOrderItemOption = commandOrderItemOption.toEntity()
                                initOrderItemOption
                            }
                        initOrderItemOptionGroup.orderItemOptionList = orderItemOptionList

                        initOrderItemOptionGroup
                    }
                initOrderItem.orderItemOptionGroupList = orderItemOptionGroupList

                initOrderItem
            }

        initOrder.orderItemList = orderItemList

        val order: Order = orderStore.store(initOrder).awaitSingle()

        return OrderInfo.Token(order.orderToken)
    }
}