package msa.gift.domain.gift

import msa.gift.common.exception.IllegalStatusException
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class Gift(
    @Id
    var id: String? = null,
    var giftToken: String,
    var buyerUserId: String,
    var orderToken: String,
    var status: Status? = null,
    var pushType: PushType? = null,
    var giftReceiverName: String,
    var giftReceiverPhone: String,
    var giftMessage: String,
    var receiverName: String,
    var receiverPhone: String,
    var receiverZipcode: String,
    var receiverAddress1: String,
    var receiverAddress2: String,
    var etcMessage: String,
    var paidAt: LocalDateTime? = null,
    var pushedAt: LocalDateTime? = null,
    var acceptedAt: LocalDateTime? = null,
    var expiredAt: LocalDateTime? = null
) {
    constructor(
        buyerUserId: String,
        orderToken: String,
        pushType: PushType,
        giftReceiverName: String,
        giftReceiverPhone: String,
        giftMessage: String,
        giftToken: String,
    ) : this(
        null,
        giftToken,
        buyerUserId,
        orderToken,
        null,
        pushType,
        giftReceiverName,
        giftReceiverPhone,
        giftMessage,
        "",
        "",
        "",
        "",
        "",
        "",
        null,
        null,
        null,
        null
    )

    enum class Status(
        val description: String
    ) {
        INIT("선물 주문 생성"),
        IN_PAYMENT("결제 중"),
        ORDER_COMPLETE("주문 완료"),
        PUSH_COMPLETE("선물 링크 발송 완료"),
        ACCEPT("선물 수락"),
        DELIVERY_PREPARE("상품준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료"),
        EXPIRATION("선물 수락 만료")
    }

    enum class PushType(
        val description: String
    ) {
        KAKAO("카카오톡"),
        LMS("문자")
    }

    fun inPayment() {
        if (this.status != Status.INIT) throw IllegalStatusException("Gift inPayment")
        this.status = Status.IN_PAYMENT
    }
}