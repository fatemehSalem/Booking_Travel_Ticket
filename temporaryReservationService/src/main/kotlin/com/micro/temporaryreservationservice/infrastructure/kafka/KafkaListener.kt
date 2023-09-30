package com.micro.temporaryreservationservice.infrastructure.kafka

import com.micro.commonservice.Order
import com.micro.temporaryreservationservice.infrastructure.service.ManageTemporaryReservationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

/*
@Component
class KafkaListener(
    private val manageTemporaryReservationService: ManageTemporaryReservationService,
) {
    private val LOG: Logger = LoggerFactory.getLogger(KafkaListener::class.java)
    @KafkaListener(
        topics = ["reservation-response-topic","payment-response-topic"],
        groupId = "bookItNow"
    )
    fun onEvent(order: Order, @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String) {
        //val topic = order.topic()
        LOG.info("Received in order-Service from topic $topic: $order")
        val orderReservation: Order
        val orderPayment: Order
        when (topic) {
            "reservation-response-topic" -> {
                orderReservation = Order(
                    id = order.id,
                    customerId = order.customerId,
                    productId = order.productId,
                    productCount = order.productCount,
                    price = order.price,
                    status = order.status
                )
            }
            "payment-response-topic" -> {
                orderPayment = Order(
                    id = order.id,
                    customerId = order.customerId,
                    productId = order.productId,
                    productCount = order.productCount,
                    price = order.price,
                    status = order.status
                )
            }
            else -> {
            }
        }
      //  manageTemporaryReservationService.confirm(orderReservation , orderPayment);
    }
}*/
