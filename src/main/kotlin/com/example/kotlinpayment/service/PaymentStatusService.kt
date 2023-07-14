package com.example.kotlinpayment.service

import OrderStatus
import TransactionStatus
import TransactionType
import com.example.kotlinpayment.domain.Order
import com.example.kotlinpayment.domain.OrderTransaction
import com.example.kotlinpayment.domain.PaymentUser
import com.example.kotlinpayment.exception.ErrorCode
import com.example.kotlinpayment.exception.PaymentException
import com.example.kotlinpayment.repository.OrderRepository
import com.example.kotlinpayment.repository.OrderTransactionRepository
import com.example.kotlinpayment.repository.PaymentUserRepository
import com.example.kotlinpayment.util.generateOrderId
import com.example.kotlinpayment.util.generateTransactionId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 결제의 요청 저장, 성공, 실패 저장
 */
@Service
class PaymentStatusService(
    private val paymentUserRepository: PaymentUserRepository,
    private val orderRepository: OrderRepository,
    private val orderTransactionRepository: OrderTransactionRepository,
) {
    @Transactional
    fun savePayRequest(
        payUserId: String,
        amount: Long,
        orderTitle: String,
        merchantTransactionId: String,
    ): Long {
        //order, orderTransaction 저장
        val paymentUser: PaymentUser = paymentUserRepository.findByPayUserId(payUserId)
            ?: throw PaymentException(ErrorCode.INVALID_REQUEST, "사용자 없음 : $payUserId")

        val order = orderRepository.save(
            Order(
                orderId = generateOrderId(),
                paymentUser = paymentUser,
                orderStatus = OrderStatus.CREATED,
                orderTile = orderTitle,
                orderAmount = amount,
            )
        )

        orderTransactionRepository.save(
            OrderTransaction(
                transactionId = generateTransactionId(),
                order = order,
                transactionType = TransactionType.PAYMENT,
                transactionStatus = TransactionStatus.RESERVE,
                transactionAmount = amount,
                merchantTransactionId = merchantTransactionId,
                description = orderTitle
            )
        )

        return order.id ?: throw PaymentException(ErrorCode.INTERNAL_SERVER_ERROR)
    }
}


