package com.micro.temporaryreservationservice.domain.model

import java.math.BigDecimal

data class TempReservationRequest(
    val productId: Long,
    val customerId: Long,
    val price: BigDecimal,
    val quantity: Int

    //other properties could be added....
)
