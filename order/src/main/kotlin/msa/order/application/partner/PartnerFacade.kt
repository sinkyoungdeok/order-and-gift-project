package msa.order.application.partner

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class PartnerFacade {

    fun registerPartner(): Flux<String> {
        return Flux.just("test")
    }
}