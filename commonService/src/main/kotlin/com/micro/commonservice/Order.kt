package com.micro.commonservice

import java.math.BigDecimal

data class Order(
    val id: Long,
    val customerId: Long,
    val productId: Long,
    val price: BigDecimal,
    var status: String,
    val productCount: Int
    // other properties ....
)
