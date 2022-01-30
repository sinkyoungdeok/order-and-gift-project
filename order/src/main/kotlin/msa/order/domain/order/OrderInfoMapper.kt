package msa.order.domain.order

import msa.order.domain.order.item.OrderItem
import msa.order.domain.order.item.OrderItemOption
import msa.order.domain.order.item.OrderItemOptionGroup
import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface OrderInfoMapper {

    @Mappings(
        Mapping(source = "order.id", target = "orderId"),
        Mapping(source = "order.deliveryFragment", target = "deliveryInfo"),
        Mapping(expression = "java(order.calculateTotalAmount())", target = "totalAmount"),
        Mapping(expression = "java(order.getStatus().name())", target = "status"),
        Mapping(
            expression = "java(order.getStatus().getDescription())",
            target = "statusDescription"
        )
    )
    fun of(
        order: Order,
        orderItemList: List<OrderItem>
    ): OrderInfo.Main

    @Mappings(
        Mapping(
            expression = "java(orderItem.getDeliveryStatus().name())",
            target = "deliveryStatus"
        ),
        Mapping(
            expression = "java(orderItem.getDeliveryStatus().getDescription())",
            target = "deliveryStatusDescription"
        ),
        Mapping(expression = "java(orderItem.calculateTotalAmount())", target = "totalAmount")
    )
    fun of(orderItem: OrderItem): OrderInfo.OrderItemInfo

    fun of(
        orderItemOptionGroup: OrderItemOptionGroup
    ): OrderInfo.OrderItemOptionGroupInfo

    fun of(
        orderItemOption: OrderItemOption
    ): OrderInfo.OrderItemOptionInfo
}