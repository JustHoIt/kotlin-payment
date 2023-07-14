package com.example.kotlinpayment.domain

import OrderStatus
import jakarta.persistence.*


@Entity
@Table(name = "orders")
class Order(
    val orderId: String,
    @ManyToOne
    val paymentUser: PaymentUser,

    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus,
    val orderTile: String,
    val orderAmount: Long,
    var paidAmount: Long = 0,
    var refundedAmount: Long = 0,
) : BaseEntity()