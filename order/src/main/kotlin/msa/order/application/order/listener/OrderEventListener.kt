package msa.order.application.order.listener

import msa.order.application.order.event.OrderKakaoEvent
import msa.order.domain.notification.NotificationService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class OrderEventListener(
    val notificationService: NotificationService
) {
    @EventListener
    @Async("notificationEventHandler")
    fun handleKakao(event: OrderKakaoEvent) {
        Thread.sleep(5000L) // 카카오 알림 발송하는데 걸리는 시간
        notificationService.sendKakao("ORDER_COMPLETE", "content")
    }
}