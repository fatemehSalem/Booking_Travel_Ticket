package com.micro.temporaryreservationservice.model

data class TempReservationRequest(
    val productId: Long,
    val customerId: Long,
    val price: Int,
    val status: String,
    val productCount: Int

    //other properties will be added....
)
