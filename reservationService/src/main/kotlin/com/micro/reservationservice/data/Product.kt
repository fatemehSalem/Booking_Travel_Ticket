package com.micro.reservationservice.data

import jakarta.persistence.*


@Entity
@Table(name = "ticket")
data class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: Long,

    @Column
     val name: String,

    @Column
    var availableItems: Int,

    @Column
    var reservedItems: Int,

){
    constructor() : this(0, "",0,0) {

    }
}