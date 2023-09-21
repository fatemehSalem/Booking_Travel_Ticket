package com.micro.reservationservice.kafka

import com.micro.commonservice.Order
import com.micro.reservationservice.service.ManageReservationService
import org.springframework.stereotype.Component
import org.springframework.kafka.annotation.KafkaListener
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
class KafkaListener(
    private val manageReservationService: ManageReservationService,
) {
    private val LOG: Logger = LoggerFactory.getLogger(KafkaListener::class.java)

    @KafkaListener(
        topics = ["temp-reservation-topic"],
        groupId = "bookItNow"
    )
    fun onEvent(order: Order) {

        // val topic = order.topic()
        LOG.info("Received in Reservation-Service: ", order);
        if(order.status == "NEW")
            manageReservationService.reserve(order);
        else
            manageReservationService.confirm(order)
    }
}