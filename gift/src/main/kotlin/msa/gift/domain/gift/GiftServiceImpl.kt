package msa.gift.domain.gift

import msa.gift.domain.gift.order.OrderApiCaller
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class GiftServiceImpl(
    val giftToOrderMapper: GiftToOrderMapper,
    val orderApiCaller: OrderApiCaller,
    val giftStore: GiftStore,
    val giftInfoMapper: GiftInfoMapper,
    val giftReader: GiftReader
) : GiftService {
    @Transactional
    override suspend fun registerOrder(
        command: GiftCommand.RegisterOrder
    ): GiftInfo.Main {
        var orderCommand = giftToOrderMapper.of(command)
        var orderToken = orderApiCaller.registerGiftOrder(orderCommand)
        var initGift = command.toEntity(orderToken)
        var gift = giftStore.store(initGift)
        return giftInfoMapper.of(gift)
    }

    @Transactional
    override suspend fun requestPaymentProcessing(giftToken: String) {
        var gift = giftReader.getGiftBy(giftToken)
        gift.inPayment()
        giftStore.store(gift)
    }

    @Transactional
    override fun completePayment(orderToken: String) {
        var gift: Mono<Gift> = giftReader.getGiftByOrderToken(orderToken)
        gift.flatMap {
            it.completePayment()
            giftStore.storeMono(it)
        }.block()
    }

    override suspend fun acceptGift(command: GiftCommand.AcceptGift) {
        TODO("Not yet implemented")
    }
}