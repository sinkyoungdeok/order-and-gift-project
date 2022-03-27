package msa.order.common.response

enum class ErrorCode(
    val errorMsg: String
) {
    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),  // 장애 상황
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ENTITY_NOT_FOUND("존재하지 않는 엔티티입니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),
    NOT_FOUND_USER("존재하지 않는 이름입니다."),
    DUPLICATE_USER("이미 존재하는 아이디입니다."),
    INVALID_TOKEN_EXCEPTION("유효 하지 않는 토큰입니다."),

    // GIFT
    GIFT_NOT_RECEIVABLE_CONDITION("선물 수락이 가능한 상태가 아닙니다."), GIFT_NOT_MODIFY_DELIVERY_CONDITION("배송지 변경이 가능한 상태가 아닙니다.");

    fun getErrorMsg(vararg arg: Any?): String {
        return String.format(errorMsg!!, *arg)
    }
}
