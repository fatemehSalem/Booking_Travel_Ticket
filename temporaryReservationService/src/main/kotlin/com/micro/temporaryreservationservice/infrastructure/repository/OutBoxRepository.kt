package com.micro.temporaryreservationservice.infrastructure.repository

import com.micro.temporaryreservationservice.domain.entity.OutBox
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OutBoxRepository : JpaRepository<OutBox, Long> {
}