package com.micro.temporaryreservationservice.api

import com.micro.temporaryreservationservice.domain.model.TempReservationRequest
import com.micro.temporaryreservationservice.infrastructure.service.TempReservationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tempReservations")
class TempReservationController(
    private val tempReservationService: TempReservationService
) {
   // private val LOG: Logger = LoggerFactory.getLogger(TempReservationController::class.java)

    @PostMapping
    fun createTemporaryReservation(@RequestBody request: TempReservationRequest) {
        tempReservationService.saveTempReservationAndOutbox(request)

    }

}