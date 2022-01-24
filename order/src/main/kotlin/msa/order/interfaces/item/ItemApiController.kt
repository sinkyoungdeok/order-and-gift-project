package msa.order.interfaces.item

import msa.order.common.response.CommonResponse
import msa.order.domain.item.ItemCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/items")
class ItemApiController(val itemDtoMapper: ItemDtoMapper) {

    @PostMapping
    fun registerItem(
        @RequestBody @Valid request: Mono<ItemDto.RegisterItemRequest>
    ): Mono<CommonResponse<ItemCommand.RegisterItemRequest>> {
        var itemCommand = request.map { itemDtoMapper.of(it) }

        return itemCommand.map { CommonResponse(it) }
    }
}