package msa.order.domain.order

class OrderInfo {

    class Token(
        var orderToken: String? = null
    ) {
        constructor(): this(null)
    }
}