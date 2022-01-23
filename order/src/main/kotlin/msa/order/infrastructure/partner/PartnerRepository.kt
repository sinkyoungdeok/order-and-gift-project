package msa.order.infrastructure.partner

import msa.order.domain.Partner
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PartnerRepository : ReactiveMongoRepository<Partner, String>