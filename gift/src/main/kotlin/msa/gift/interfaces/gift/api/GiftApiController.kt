package msa.gift.interfaces.gift.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/gifts")
class GiftApiController(
    val giftDtoMapper: GiftDtoMapper
) {

    @PostMapping
    fun registerOrder(
        @RequestBody @Valid request: GiftDto.RegisterOrderRequest
    ) {
        var giftCommand = giftDtoMapper.of(request)
        println(giftCommand)
    }
}