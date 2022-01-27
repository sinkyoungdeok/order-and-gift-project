package msa.order.interfaces.item

import msa.order.application.item.ItemFacade
import msa.order.common.response.CommonResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/items")
class ItemApiController(
    val itemFacade: ItemFacade,
    val itemDtoMapper: ItemDtoMapper
) {

    @PostMapping
    fun registerItem(
        @RequestBody @Valid request: ItemDto.RegisterItemRequest
    ): Mono<CommonResponse<ItemDto.RegisterResponse>> {
        var partnerToken = request.partnerToken
        var itemCommand = itemDtoMapper.of(request)
        var itemInfo = itemFacade.registerItem(itemCommand, partnerToken)
        var response = itemInfo.map { itemDtoMapper.of(it) }
        return response.map { CommonResponse(it) }
    }
}