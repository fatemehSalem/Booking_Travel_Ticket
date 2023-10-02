package com.micro.reservationservice.kafka

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.micro.commonservice.Order
import com.micro.reservationservice.service.ManageReservationService
import org.springframework.stereotype.Component
import org.springframework.kafka.annotation.KafkaListener
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Payload

@Component
class KafkaListener(
) {
    private val LOG: Logger = LoggerFactory.getLogger(KafkaListener::class.java)
    val objectMapper = ObjectMapper()

    @KafkaListener(topics = ["reservation-request"])
    fun onEvent(@Payload data: String) {
        val event: JsonNode = objectMapper.readValue(data, JsonNode::class.java)
        LOG.info("received in reservation-service from kafka" )
    }
}