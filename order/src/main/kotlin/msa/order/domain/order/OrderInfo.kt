package msa.order.domain.order

class OrderInfo {

    data class Token(
        var orderToken: String? = null
    ) {
        constructor(): this(null)
    }
}