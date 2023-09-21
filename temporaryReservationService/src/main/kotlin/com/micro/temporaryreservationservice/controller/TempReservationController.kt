package com.micro.temporaryreservationservice.controller

import com.micro.commonservice.Order
import com.micro.temporaryreservationservice.config.TempReservationConfig
import com.micro.temporaryreservationservice.data.TempReservation
import com.micro.temporaryreservationservice.data.TempReservationRepository
import com.micro.temporaryreservationservice.model.TempReservationRequest
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
@RequestMapping("/tempReservation")
class TempReservationController(
    private val tempReservationRepository: TempReservationRepository,
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val LOG: Logger = LoggerFactory.getLogger(TempReservationController::class.java)

    @PostMapping
    fun createTemporaryReservation(@RequestBody request: TempReservationRequest): ResponseEntity<Any> {
        val temp = TempReservation()
        temp.customerId = request.customerId
        temp.productId = request.productId
        temp.status = "NEW"
        temp.price = request.price
        temp.productCount = request.productCount
        temp.createdAt = LocalDateTime.now()
        temp.updatedAt = LocalDateTime.now()

        //check validation for ticket Temporary reservation (check total capacity and ....)
        //we will use Temporary Reservation Service here
        //.....
        //tempReservationConfig.ticketCapacity
        //.....
        //
        val savedTempReservation = tempReservationRepository.save(temp)
        LOG.info("Sent: {}", savedTempReservation)

        kafkaTemplate.send(
            "temp-reservation-topic", Order(
                savedTempReservation.reservationId,
                savedTempReservation.customerId,
                savedTempReservation.productId,
                savedTempReservation.price,
                savedTempReservation.status,
                savedTempReservation.productCount
            )
        )

        return ResponseEntity.ok(savedTempReservation)
    }

}