package msa.order.domain.order

import msa.order.domain.item.ItemReader
import msa.order.domain.order.item.OrderItem
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class OrderServiceImpl(
    val orderStore: OrderStore,
    val itemReader: ItemReader
) : OrderService {

    @Transactional
    override fun registerOrder(orderCommand: OrderCommand.RegisterOrder): Mono<OrderInfo.Token> {
        val initOrder = orderCommand.toEntity()
        orderCommand.orderItemList.map {
            var item = itemReader.getItemBy(it.itemToken)

            item.map { it2 ->
                val toEntity: OrderItem = it.toEntity(it2)


                initOrder.orderItemList.add(toEntity)
                toEntity
            }
        }
        val order = orderStore.store(orderCommand.toEntity())
        return Mono.just(OrderInfo.Token("test"))
    }
}