package com.micro.reservationservice.model

data class OrderValidationModel(
    var orderProductCount : Int,
    var productAvailableItems: Int,
    var productReservedItems: Int
)
