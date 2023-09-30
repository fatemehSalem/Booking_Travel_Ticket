package com.micro.temporaryreservationservice.domain.entity


import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "temporaryReservation")
data class TempReservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reservationId: Long,

    @Column
    var customerId: Long,

    @Column
    var productId: Long,

    @Column
    var price: BigDecimal,

    @Column
    var quantity: Int,

    //....other properties could be added....

    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(0, 0, 0, BigDecimal.ZERO, 0, LocalDateTime.now(), LocalDateTime.now())
}
