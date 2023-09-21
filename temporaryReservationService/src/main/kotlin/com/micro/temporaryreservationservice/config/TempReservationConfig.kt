package com.micro.temporaryreservationservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.sql.Timestamp

@Configuration
data class TempReservationConfig(
    @Value("\${ticket.capacity}")
    val ticketCapacity: Int,

    @Value("\${ticket.price}")
    val ticketPrice: Double,

    @Value("\${ticket.reserved_seats}")
    val ticketReservedSeats: Int,

    @Value("\${ticket.available_times.time}")
    val time: Timestamp,
    //val availableTimes: List<TimeSlot>

    @Value("\${ticket.available_times.available_seats}")
    val available_seats: Int
)

data class TimeSlot(
    val time: Timestamp,
    val available_seats: Int
)

