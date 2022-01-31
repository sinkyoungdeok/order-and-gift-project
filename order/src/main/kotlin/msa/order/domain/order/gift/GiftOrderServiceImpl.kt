package msa.order.domain.order.gift

import msa.order.domain.order.OrderCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GiftOrderServiceImpl : GiftOrderService {
    @Transactional
    override fun paymentOrder(command: OrderCommand.PaymentRequest) {
        TODO("Not yet implemented")
    }
}