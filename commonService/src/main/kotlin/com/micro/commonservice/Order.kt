package com.micro.commonservice

data class Order(
    val id: Long,
    val customerId: Long,
    val productId: Long,
    val price: Int,
    var status: String,
    val productCount: Int
    // other properties ....
)
