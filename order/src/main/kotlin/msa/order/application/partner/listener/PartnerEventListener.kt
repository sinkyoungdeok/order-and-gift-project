package msa.order.application.partner.listener

import msa.order.application.partner.event.PartnerEmailEvent
import msa.order.domain.notification.NotificationService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class PartnerEventListener(
    val notificationService: NotificationService
) {

    @EventListener
    @Async("notificationEventHandler")
    fun handleEmail(event: PartnerEmailEvent) {
        Thread.sleep(5000L) // 이메일 발송하는데 걸리는 시간
        notificationService.sendEmail(event.email, "title", "description")
    }
}