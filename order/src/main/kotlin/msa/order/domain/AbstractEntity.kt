package msa.order.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.time.ZonedDateTime


abstract class AbstractEntity(
    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    constructor(): this(LocalDateTime.now(), LocalDateTime.now())
}
