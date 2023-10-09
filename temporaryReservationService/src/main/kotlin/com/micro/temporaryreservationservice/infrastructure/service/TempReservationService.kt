package com.micro.temporaryreservationservice.infrastructure.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.micro.temporaryreservationservice.api.TempReservationController
import com.micro.temporaryreservationservice.domain.entity.OutBox
import com.micro.temporaryreservationservice.domain.entity.TempReservation
import com.micro.temporaryreservationservice.domain.model.TempReservationRequest
import com.micro.temporaryreservationservice.domain.model.TempReservationStatus
import com.micro.temporaryreservationservice.infrastructure.repository.OutBoxRepository
import com.micro.temporaryreservationservice.infrastructure.repository.TempReservationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TempReservationService(
    private val tempReservationRepository: TempReservationRepository,
    private val outBoxRepository: OutBoxRepository,

    ) {
    private val LOG: Logger = LoggerFactory.getLogger(TempReservationController::class.java)

   @Transactional
    fun saveTempReservationAndOutbox(request: TempReservationRequest){
       val temp = TempReservation()
       temp.customerId = request.customerId
       temp.productId = request.productId
       temp.price = request.price
       temp.quantity = request.quantity
       temp.createdAt = LocalDateTime.now()
       temp.updatedAt = LocalDateTime.now()

       val savedTempReservation = tempReservationRepository.save(temp)
       LOG.info("Persist TempReservation : {}", savedTempReservation)

       val objectMapper = ObjectMapper().registerModule(JavaTimeModule())
       val tempReservationJson = objectMapper.writeValueAsString(savedTempReservation)

       val outbox = OutBox()
       outbox.aggregateId = savedTempReservation.reservationId
       outbox.aggregateType = "TEMP-RESERVATION"
       outbox.eventType = TempReservationStatus.CREATED.toString()
       outbox.payload = tempReservationJson
       val savedOutBox = outBoxRepository.save(outbox)
       LOG.info("Persist Outbox : {}", savedOutBox)
    }
}