package msa.order.interfaces.item

import msa.order.application.item.ItemFacade
import msa.order.common.response.CommonResponse
import org.springframework.web.bind.annotation.*
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

    @PostMapping("/change-on-sales")
    fun changeOnSaleItem(
        @RequestBody @Valid request: ItemDto.ChangeStatusItemRequest
    ): Mono<CommonResponse<ItemDto.RegisterResponse>> {
        var itemToken = request.itemToken
        var itemInfo = itemFacade.changeOnSaleItem(itemToken)
        var response = itemInfo.map { itemDtoMapper.of(it) }
        return response.map { CommonResponse(it) }
    }

    @PostMapping("/change-end-of-sales")
    fun changeEndOfSaleItem(
        @RequestBody @Valid request: ItemDto.ChangeStatusItemRequest
    ): Mono<CommonResponse<ItemDto.RegisterResponse>> {
        var itemToken = request.itemToken
        var itemInfo = itemFacade.changeEndOfSaleItem(itemToken)
        var response = itemInfo.map { itemDtoMapper.of(it) }
        return response.map { CommonResponse(it) }
    }

    @GetMapping("/{itemToken}")
    fun retrieve(@PathVariable("itemToken") itemToken: String) {
        var itemInfo = itemFacade.retrieveItemInfo(itemToken)
//        var response = itemInfo.map { itemDtoMapper.of(it) }
//        return response.map { CommonResponse(it) }
    }
}