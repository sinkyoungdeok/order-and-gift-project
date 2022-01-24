package msa.order.infrastructure.partner

import msa.order.domain.partner.Partner
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface PartnerRepository : ReactiveMongoRepository<Partner, String> {
    fun findByPartnerName(partnerToken: String): Mono<Partner>
}