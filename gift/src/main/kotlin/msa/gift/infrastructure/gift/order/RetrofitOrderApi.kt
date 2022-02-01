package msa.gift.infrastructure.gift.order

import msa.gift.common.response.CommonResponse
import msa.gift.domain.gift.GiftCommand
import msa.gift.domain.gift.order.OrderApiCommand
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitOrderApi {
    @POST("api/v1/gift-orders/init")
    fun registerOrder(
        @Body request: OrderApiCommand.RegisterOrder
    ): Call<CommonResponse<RetrofitOrderApiResponse.RegisterOrder>>

    @POST("api/v1/gift-orders/{orderToken}/update-receiver-info")
    fun updateReceiverInfo(
        @Path("orderToken") orderToken: String,
        @Body request: GiftCommand.AcceptGift
    ): Call<Void>
}