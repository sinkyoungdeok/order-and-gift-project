package msa.gift.infrastructure.gift.order

import msa.gift.common.response.CommonResponse
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
        val call = retrofitOrderApi.registerOrder(command)
        val response: CommonResponse<RetrofitOrderApiResponse.RegisterOrder>? = retrofitUtils.responseSync(call)
        if (response != null) {
            return response.data.orderToken
        }
        return ""
    }
}
