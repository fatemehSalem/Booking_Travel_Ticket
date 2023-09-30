package com.micro.temporaryreservationservice.infrastructure.service

import com.micro.commonservice.Order
import org.springframework.stereotype.Service

@Service
class ManageTemporaryReservationService {
   fun confirm(orderPayment: Order, orderReservation: Order): Order? {
       val o = Order(
           id = orderPayment.id,
           customerId = orderPayment.customerId,
           productId = orderPayment.productId,
           price = orderPayment.price,
           status = orderPayment.status,
           productCount = orderPayment.productCount,
       )
       if (orderPayment.status == "ACCEPT"){
           if(orderReservation.status == "ACCEPT")
               o.status = "CONFIRMED"
       } else {
           if(orderReservation.status == "ACCEPT" || orderReservation.status == "REJECT")
               o.status = "ROLLBACK"
       }

       return o
    }
}