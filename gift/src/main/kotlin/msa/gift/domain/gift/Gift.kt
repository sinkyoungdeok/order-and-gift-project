package msa.gift.domain.gift

import msa.gift.common.exception.IllegalStatusException
import msa.gift.common.exception.InvalidParamException
import org.apache.commons.lang3.StringUtils
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
        status: Status
    ) : this(
        null,
        giftToken,
        buyerUserId,
        orderToken,
        status,
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

    fun completePayment() {
        if (this.status != Status.IN_PAYMENT) throw IllegalStatusException("Gift paymentComplete");
        this.status = Status.ORDER_COMPLETE
        this.paidAt = LocalDateTime.now()
    }

    fun accept(command: GiftCommand.AcceptGift) {
        var receiverName = command.receiverName
        var receiverPhone = command.receiverPhone
        var receiverZipcode = command.receiverZipcode
        var receiverAddress1 = command.receiverAddress1
        var receiverAddress2 = command.receiverAddress2
        var etcMessage = command.etcMessage

        if (!availableAccept()) throw IllegalStatusException()
        if (StringUtils.isEmpty(receiverName)) throw InvalidParamException("Gift accept receiverName is empty")
        if (StringUtils.isEmpty(receiverPhone)) throw InvalidParamException("Gift accept receiverPhone is empty")
        if (StringUtils.isEmpty(receiverZipcode)) throw InvalidParamException("Gift accept receiverZipcode is empty")
        if (StringUtils.isEmpty(receiverAddress1)) throw InvalidParamException("Gift accept receiverAddress1 is empty")
        if (StringUtils.isEmpty(receiverAddress2)) throw InvalidParamException("Gift accept receiverAddress2 is empty")
        if (StringUtils.isEmpty(etcMessage)) throw InvalidParamException("Gift accept etcMessage is empty")

        this.status = Status.ACCEPT
        this.receiverName = receiverName
        this.receiverPhone = receiverPhone
        this.receiverZipcode = receiverZipcode
        this.receiverAddress1 = receiverAddress1
        this.receiverAddress2 = receiverAddress2
        this.etcMessage = etcMessage
        this.acceptedAt = LocalDateTime.now()
    }

    private fun availableAccept(): Boolean {
        return if (expiredAt != null && expiredAt!!.isBefore(LocalDateTime.now())) false
        else status == Status.ORDER_COMPLETE ||
                status == Status.PUSH_COMPLETE
    }
}