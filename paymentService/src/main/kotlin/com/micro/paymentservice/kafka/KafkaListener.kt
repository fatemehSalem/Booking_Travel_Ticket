package com.micro.paymentservice.kafka

import com.micro.commonservice.Order
import com.micro.paymentservice.service.ManageReservationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaListener(
    private val manageReservationService: ManageReservationService,
) {
    private val LOG: Logger = LoggerFactory.getLogger(KafkaListener::class.java)
    @KafkaListener(
        topics = ["reservation-response-topic"],
        groupId = "bookItNow"
    )
    fun onEvent(order: Order) {
        // val topic = order.topic()
        LOG.info("Received in Payment-Service from reservation service: ", order);
        if(order.status == "ACCEPT")
            manageReservationService.reserve(order);
        else
            manageReservationService.confirm(order);
    }

    @KafkaListener(
        topics = ["temp-reservation-topic"],
        groupId = "bookItNow"
    )
    fun onEventTemp(order: Order) {
        // val topic = order.topic()
        LOG.info("Received in Payment-Service from temp-reservation service: ", order);
        manageReservationService.confirm(order);
    }
}