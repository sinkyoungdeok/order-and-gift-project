package msa.order.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime


abstract class AbstractEntity(
    @CreatedDate
    var createdAt: ZonedDateTime? = null,

    @LastModifiedDate
    var updatedAt: ZonedDateTime? = null
) {
    constructor(): this(null, null)
}
