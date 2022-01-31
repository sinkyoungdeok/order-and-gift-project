package msa.gift.infrastructure.gift.order

import msa.gift.domain.gift.order.OrderApiCaller
import msa.gift.domain.gift.order.OrderApiCommand
import org.springframework.stereotype.Component

@Component
class OrderApiCallerImpl : OrderApiCaller {
    override fun registerGiftOrder(command: OrderApiCommand.RegisterOrder): String {
        TODO("Not yet implemented")
    }
}