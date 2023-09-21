package com.micro.temporaryreservationservice.data


import jakarta.persistence.*
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
    var price: Int,

    @Column
    var status: String,

    @Column
    var productCount: Int,

    //....other properties....

    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
        constructor() : this(0, 0, 0 ,0,"",0) {

        }
}
