package com.micro.paymentservice.service

import com.micro.commonservice.Order
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class ManageReservationService(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val LOG: Logger = LoggerFactory.getLogger(ManageReservationService::class.java)

    fun reserve(order: Order) {
        //check payment process
        //if payment process was successful:
        order.status = "ACCEPT"

        //if payment process was not successful:
       // order.status = "REJECT"

        kafkaTemplate.send("payment-response-topic" , order.id.toString(), order)
        LOG.info("Sent from Payment Service: {}", order)
    }
    fun confirm(order: Order) {
        if(order.status == "CONFIRMED"){
            /////
        }

        else if (order.status == "ROLLBACK") {
            /////
        }

    }
}