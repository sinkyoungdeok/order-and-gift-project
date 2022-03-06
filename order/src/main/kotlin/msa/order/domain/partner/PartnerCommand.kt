package msa.order.domain.partner

import msa.order.common.util.TokenGenerator

class PartnerCommand {

    class RegisterPartner(
        var partnerName: String,
        var businessNo: String,
        var email: String
    ) {
        constructor() : this("", "", "")

        private val PARTNER_PREFIX = "ptn_"

        fun toEntity(userId: String): Partner {
            return Partner(
                userId = userId,
                partnerToken = TokenGenerator.randomCharacterWithPrefix(PARTNER_PREFIX) ?: "",
                partnerName = partnerName,
                businessNo = businessNo,
                email = email,
            )
        }
    }
}