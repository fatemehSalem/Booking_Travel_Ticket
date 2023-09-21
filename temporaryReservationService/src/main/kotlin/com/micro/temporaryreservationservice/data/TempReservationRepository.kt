package com.micro.temporaryreservationservice.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TempReservationRepository: JpaRepository<TempReservation, Long> {
}