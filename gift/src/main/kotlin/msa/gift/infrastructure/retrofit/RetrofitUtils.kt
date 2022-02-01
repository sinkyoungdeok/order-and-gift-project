package msa.gift.infrastructure.retrofit

import com.google.gson.GsonBuilder
import msa.gift.common.response.CommonResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Component
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

@Component
class RetrofitUtils {
    private val log = LogFactory.getLog(javaClass)
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(3, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    fun initRetrofit(baseUrl: String?): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl!!)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }

    fun <T : CommonResponse<T>> responseSync(call: Call<T>): T? {
        return try {
            val execute = call.execute()
            if (execute.isSuccessful) {
                execute.body()
            } else {
                throw RuntimeException("retrofit execute response error")
            }
        } catch (e: IOException) {
            log.error("", e)
            throw RuntimeException("retrofit execute IOException")
        }
    }

    fun responseVoid(call: Call<Void>) {
        try {
            if (!call.execute().isSuccessful) throw RuntimeException()
        } catch (e: IOException) {
            throw RuntimeException()
        }
    }
}