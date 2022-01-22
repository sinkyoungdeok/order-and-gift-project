package msa.order.interfaces.partner

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class PartnerApiRouter(private val partnerHandler: PartnerHandler) {
    @Bean
    fun partnerRoutes() = router {
        "/api/v2/partners".nest {
            POST("", partnerHandler::create)
        }
    }
}