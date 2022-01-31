package msa.gift.infrastructure.retrofit

import msa.gift.infrastructure.gift.order.RetrofitOrderApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RetrofitServiceRegistry(
    val retrofitUtils: RetrofitUtils
) {
    val baseUrl: String = "http://localhost:8080/"

    @Bean
    fun retrofitOrderApi(): RetrofitOrderApi {
        var retrofit = retrofitUtils.initRetrofit(baseUrl)
        return retrofit.create(RetrofitOrderApi::class.java)
    }

}