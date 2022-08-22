package msa.gift.infrastructure.gift.order

import msa.gift.common.response.CommonResponse
import msa.gift.domain.gift.GiftCommand
import msa.gift.domain.gift.order.OrderApiCaller
import msa.gift.domain.gift.order.OrderApiCommand
import msa.gift.infrastructure.retrofit.RetrofitUtils
import org.springframework.stereotype.Component

@Component
class OrderApiCallerImpl(
    val retrofitUtils: RetrofitUtils,
    val retrofitOrderApi: RetrofitOrderApi
) : OrderApiCaller {
    override fun registerGiftOrder(command: OrderApiCommand.RegisterOrder): String {
        val accessToken = login(OrderApiCommand.LoginRequest("giftAdmin", "admin"))

        val call = retrofitOrderApi.registerOrder("Bearer $accessToken", command)
        val response: CommonResponse<RetrofitOrderApiResponse.RegisterOrder>? =
            retrofitUtils.responseSync(call)
        if (response != null) {
            return response.data.orderToken
        }
        return ""
    }

    override fun updateReceiverInfo(orderToken: String, command: GiftCommand.AcceptGift) {
        val accessToken = login(OrderApiCommand.LoginRequest("giftAdmin", "admin"))

        val call = retrofitOrderApi.updateReceiverInfo("Bearer $accessToken", orderToken, command)
        retrofitUtils.responseVoid(call)
    }

    override fun login(command: OrderApiCommand.LoginRequest): String {
        val call = retrofitOrderApi.login(command)
        val response = retrofitUtils.responseSync(call)
        if (response != null) {
            return response.data.accessToken
        }
        return ""
    }
}
