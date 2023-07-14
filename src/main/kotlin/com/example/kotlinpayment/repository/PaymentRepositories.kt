package com.example.kotlinpayment.repository

import com.example.kotlinpayment.domain.Order
import com.example.kotlinpayment.domain.OrderTransaction
import com.example.kotlinpayment.domain.PaymentUser
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentUserRepository : JpaRepository<PaymentUser, Long> {
    fun findByPayUserId(payUserId: String): PaymentUser?
}

interface OrderRepository : JpaRepository<Order, Long> {

}

interface OrderTransactionRepository : JpaRepository<OrderTransaction, Long> {

}