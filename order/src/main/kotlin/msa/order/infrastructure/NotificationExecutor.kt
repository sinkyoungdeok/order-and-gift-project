package msa.order.infrastructure

import msa.order.domain.notification.NotificationService
import org.slf4j.LoggerFactory

class NotificationExecutor : NotificationService {
    val log = LoggerFactory.getLogger(javaClass)

    override fun sendEmail(email: String, title: String, description: String) {
        log.info("sendEmail")
    }

    override fun sendKakao(phoneNo: String, description: String) {
        log.info("sendKakao")
    }

    override fun sendSms(phoneNo: String, description: String) {
        log.info("sendSms")
    }
}