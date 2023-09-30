package com.micro.temporaryreservationservice.api

import com.micro.commonservice.Order
import com.micro.temporaryreservationservice.domain.entity.TempReservation
import com.micro.temporaryreservationservice.infrastructure.repository.TempReservationRepository
import com.micro.temporaryreservationservice.domain.model.TempReservationRequest
import com.micro.temporaryreservationservice.infrastructure.service.TempReservationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate

@RestController
@RequestMapping("/tempReservations")
class TempReservationController(

    private val kafkaTemplate: KafkaTemplate<String, Any>,
    private val tempReservationService: TempReservationService
) {
    private val LOG: Logger = LoggerFactory.getLogger(TempReservationController::class.java)

    @PostMapping
    fun createTemporaryReservation(@RequestBody request: TempReservationRequest) {


        //check validation for ticket Temporary reservation (check total capacity and ....)
        //we will use Temporary Reservation Service here
        //.....
        //tempReservationConfig.ticketCapacity
        //.....
        //

    }

}