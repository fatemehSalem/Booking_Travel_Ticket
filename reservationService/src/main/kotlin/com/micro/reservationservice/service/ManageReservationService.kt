package com.micro.reservationservice.service

import com.micro.commonservice.Order
import com.micro.reservationservice.data.Product
import com.micro.reservationservice.data.ProductRepository
import com.micro.reservationservice.interfaces.ManualReservationInterface
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class ManageReservationService(
    private val productRepository: ProductRepository,
    private val kafkaTemplate: KafkaTemplate<String, Any>,
): ManualReservationInterface {
    private val LOG: Logger = LoggerFactory.getLogger(ManageReservationService::class.java)

    //with any business...Manual or automatic reservation
    //
    fun reserve(order: Order) {
        val product: Product = productRepository.findById(order.productId).orElseThrow()
        LOG.info("Found Product in Reservation-Service: {}", product)
        if (order.status == "NEW") {
            if (order.productCount < product.availableItems) {
                product.reservedItems = product.reservedItems + order.productCount
                product.availableItems = product.availableItems - order.productCount
                order.status = "ACCEPT"
                productRepository.save(product)
                kafkaTemplate.send("reservation-response-topic", order) //to payment-service
                LOG.info("Sent from Reservation-Service: {}", order)
            } else {
                order.status = "REJECT"
                kafkaTemplate.send("reservation-response-topic", order) //to payment-service
                LOG.error("REJECT from Reservation-Service: ", order)
            }

        }
    }

    fun confirm(order: Order) {
        val product = productRepository.findById(order.productId).orElseThrow()
        LOG.info("Found: $product")

        when (order.status) {
            "CONFIRMED" -> {
                product.reservedItems -= order.productCount
                productRepository.save(product)
            }
            "ROLLBACK" -> {
                    product.reservedItems -= order.productCount
                    product.availableItems += order.productCount
                productRepository.save(product)

            }
        }
    }


}