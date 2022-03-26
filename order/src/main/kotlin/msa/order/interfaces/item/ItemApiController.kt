package msa.order.interfaces.item

import msa.order.application.item.ItemFacade
import msa.order.common.response.CommonResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/items")
class ItemApiController(
    val itemFacade: ItemFacade,
    val itemDtoMapper: ItemDtoMapper
) {

    @PostMapping
    @PreAuthorize("hasRole('PARTNER')")
    suspend fun registerItem(
        @RequestBody @Valid request: ItemDto.RegisterItemRequest
    ): CommonResponse<ItemDto.RegisterResponse> {
        var partnerToken = request.partnerToken
        var itemCommand = itemDtoMapper.of(request)
        var itemInfo = itemFacade.registerItem(itemCommand, partnerToken)
        var response = itemDtoMapper.of(itemInfo)
        return CommonResponse(response)
    }

    @PostMapping("/change-on-sales")
    @PreAuthorize("hasRole('PARTNER')")
    suspend fun changeOnSaleItem(
        @RequestBody @Valid request: ItemDto.ChangeStatusItemRequest
    ): CommonResponse<ItemDto.RegisterResponse> {
        var itemToken = request.itemToken
        var itemInfo = itemFacade.changeOnSaleItem(itemToken)
        var response = itemDtoMapper.of(itemInfo)
        return CommonResponse(response)
    }

    @PostMapping("/change-end-of-sales")
    @PreAuthorize("hasRole('PARTNER')")
    suspend fun changeEndOfSaleItem(
        @RequestBody @Valid request: ItemDto.ChangeStatusItemRequest
    ): CommonResponse<ItemDto.RegisterResponse> {
        var itemToken = request.itemToken
        var itemInfo = itemFacade.changeEndOfSaleItem(itemToken)
        var response = itemDtoMapper.of(itemInfo)
        return CommonResponse(response)
    }

    @GetMapping("/{itemToken}")
    @PreAuthorize("hasRole('PARTNER')")
    suspend fun retrieve(
        @PathVariable("itemToken") itemToken: String
    ): CommonResponse<ItemDto.Main> {
        var itemInfo = itemFacade.retrieveItemInfo(itemToken)
        var response = itemDtoMapper.of(itemInfo)
        return CommonResponse(response)
    }
}