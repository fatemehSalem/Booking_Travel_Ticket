package com.micro.temporaryreservationservice.infrastructure.repository

import com.micro.temporaryreservationservice.domain.entity.TempReservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TempReservationRepository: JpaRepository<TempReservation, Long> {
}