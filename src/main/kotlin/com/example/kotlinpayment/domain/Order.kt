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
    val oderAmount: Long,
    var paidAmount: Long,
    var refundedAmount: Long,
) : BaseEntity()