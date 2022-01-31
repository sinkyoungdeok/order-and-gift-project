package msa.gift.domain.gift

import org.springframework.data.mongodb.core.mapping.Document

@Document
class Gift {
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
}