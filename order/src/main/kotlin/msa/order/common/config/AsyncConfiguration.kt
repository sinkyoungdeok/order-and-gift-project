package msa.order.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Configuration
@EnableAsync
class AsyncConfiguration {

    @Value("\${threadPool.notification.event}")
    val threadPoolNotificationEvent = 8

    @Bean(name = arrayOf("notificationEventHandler"))
    fun emailEventHandlerTaskExecutor(): ExecutorService {
        return Executors.newFixedThreadPool(threadPoolNotificationEvent)
    }
}