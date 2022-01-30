package msa.order.application.item.listener

import msa.order.application.item.event.ItemEmailEvent
import msa.order.domain.notification.NotificationService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class ItemEventListener(
    val notificationService: NotificationService
) {

    @EventListener
    @Async("notificationEventHandler")
    fun handleEmail(event: ItemEmailEvent) {
        Thread.sleep(5000L) // 이메일 발송하는데 걸리는 시간
        notificationService.sendEmail("", "title", "description")
    }
}