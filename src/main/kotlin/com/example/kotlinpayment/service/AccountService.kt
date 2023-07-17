package com.example.kotlinpayment.service

import com.example.kotlinpayment.adapter.AccountAdapter
import com.example.kotlinpayment.adapter.UseBalanceRequest
import com.example.kotlinpayment.exception.ErrorCode
import com.example.kotlinpayment.exception.PaymentException
import com.example.kotlinpayment.repository.OrderRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AccountService(
        private val accountAdapter: AccountAdapter,
        private val orderRepository: OrderRepository,
) {
    @Transactional
    fun useAccount(orderId: Long): String {
        // 계좌 사용 요청 및 처리
        val order = orderRepository.findById(orderId)
                .orElseThrow { throw PaymentException(ErrorCode.ORDER_NOT_ROUND) }

        return accountAdapter.useAccount(
                UseBalanceRequest(
                        userId = order.paymentUser.accountUserId,
                        accountNumber = order.paymentUser.accountNumber,
                        amount = order.orderAmount
                )
        ).transactionId
    }
}